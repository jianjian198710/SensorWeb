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
	
	private ServerSocket serverSocket = null;
	private static final int PORT = 7878;
	private ExecutorService executor;
	
	public StartServerSocket(){
		executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*50);
	}
	
	@Override
	public void run(){
		startServerSocket();
		startReceive();
	}
	
	public void startServerSocket(){
		if(serverSocket==null){
			try {
				serverSocket = new ServerSocket(PORT);
				System.out.println("����ServerSocket�ɹ�!");
			} catch(IOException e) {
				System.out.println("����ServerSocket����!");
			}
		}else{
			return;
		}
	}
	
	public void startReceive(){
		while(true){
			try {
				Socket socket = serverSocket.accept();
				executor.execute(new DataProcessor(socket));
			} catch(IOException e) {
				e.printStackTrace();
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
						System.out.println("������Ϣ!!!"+s);
						Data data = new Data();
						data.setSensorID(receinfo[0]);
						data.setValue(receinfo[2]);
						data.setObservableProperty(receinfo[1]);
						data.setUom(receinfo[3]);
						//��ʱ���
						data.setTimeStamp(formatter.format(new Date()));
						isi.insert(data);
					}else{
						System.out.println("������Ϣ!!!From"+receinfo[0]+": "+s);
					}
				}
			}catch(IOException e){
				System.out.println("��ȡSocket���󣡣���");
			}
		}
	}
}
