package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import JavaBean.Data;
import JavaBean.Sensor;

public class TCPServer{
	private boolean flag;
	private static TCPServer instance;
	private ServerSocket serverSocket = null;
	private ExecutorService executor;

	private TCPServer() {
		executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*50);
	}

	// 通过单例模式创建TCPServer
	public TCPServer getInstance() {
		if (instance == null) {
			synchronized (TCPServer.class) {
				if (instance == null) {
					instance = new TCPServer();
				}
				return instance;
			}
		}
		return instance;
	}

	public void start() throws IOException {
		if(flag == true) {
			serverSocket = new ServerSocket(30000);
			Socket socket = serverSocket.accept();
			executor.execute(new DataProcessor(socket));
		}
	}
	
	private final class DataProcessor implements Runnable{
		private Socket socket;
		private DataProcessor(Socket socket){
			this.socket = socket;
		}
		@Override
		public void run(){
			try(BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
				String s = null;
				while((s = br.readLine())!=null){
					//sensorID+"##Temperature##"+data+"##degree";
					String[] receinfo = s.split("##");
					Sensor sensor = new Sensor();
					Data data = new Data();
					sensor.setSensorID(receinfo[0]);
					sensor.setObservableProperty(receinfo[1]);
					sensor.setUom(receinfo[3]);
					data.setValue(receinfo[2]);
					data.setSensor(sensor);
				}
			}catch(IOException e){
				System.out.println("读取Socket错误！！！");
			}
		}
	}
}
