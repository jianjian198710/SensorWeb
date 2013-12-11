package sensorweb.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import sensorweb.javaBean.Data;
import sensorweb.service.InsertServiceImp;

public class StartServerSocket implements Runnable{
	
	private static StartServerSocket startServerSocket;
	
	private ServerSocket serverSocket = null;
	private static final int PORT = 7878;
	private ExecutorService executor;
	
	private StartServerSocket(){
		executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*50);
	}
	
	public static StartServerSocket getInstance(){
		if(startServerSocket==null){
			startServerSocket =  new StartServerSocket();
		}
		return startServerSocket;
	}
	
	
	@Override
	public void run(){
		startReceive();
	}
	
	public void startServerSocket(){
		if(serverSocket==null){
			try {
				serverSocket = new ServerSocket(PORT);
				System.out.println("创建ServerSocket成功!");
			} catch(IOException e) {
				System.out.println("创建ServerSocket出错!");
			}
		}else{
			return;
		}
	}
	
	public void closeServerSocket(){
		try {
			getServerSocket().close();
			System.out.println("关闭ServerSocket成功!");
		} catch (IOException e) {
			System.out.println("关闭ServerSocket失败!");
		}
	}
	
	public void startReceive(){
		while(true){
			try{
				Socket socket = serverSocket.accept();
				executor.execute(new DataProcessor(socket));
			} catch(IOException e) {
				break;
			}
		}
	}
	
	public void start(){
		new Thread(this).start();
	}
	
	private final class DataProcessor implements Runnable{
		private Socket socket;
		private DataProcessor(Socket socket){
			this.socket = socket;
		}
		@Override
		public void run(){
			try(BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(),Charset.forName("UTF-8")))){
				System.out.println(br.readLine());
				String s = null;
				InsertServiceImp isi = new InsertServiceImp();
				SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
				while((s = br.readLine())!=null){
					//sensorID+"##Temperature##"+data+"##degree";
					String[] receinfo = s.split("##");
					if(TCPServer.getInstance().getSensorStatus().containsKey(receinfo[0])&&
							TCPServer.getInstance().getSensorStatus().get(receinfo[0])==TCPServer.SENSOR_START){
						System.out.println("有用信息!!!"+s);
						Data data = new Data();
						data.setSensorID(receinfo[0]);
						data.setValue(receinfo[2]);
						data.setObservableProperty(receinfo[1]);
						data.setUom(receinfo[3]);
						//打时间戳
						data.setTimeStamp(formatter.format(new Date()));
						isi.insert(data);
					}else{
						System.out.println("无用消息!!!From"+receinfo[0]+": "+s);
					}
				}
			}catch(IOException e){
				e.printStackTrace();
				System.out.println("读取Socket错误！！！");
			}
		}
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}
}
