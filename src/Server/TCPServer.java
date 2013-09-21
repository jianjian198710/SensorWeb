package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import JavaBean.Data;
import JavaBean.Sensor;
import Service.HibernateUtil;
import Service.InsertServiceImp;

public class TCPServer{
	private boolean flag;
	private static TCPServer instance;
	private ServerSocket serverSocket = null;
	private ExecutorService executor;

	private TCPServer() {
		executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*50);
	}
	
	private ArrayList<String> allSensorID = new ArrayList<String>();
	private HashSet<String> sensorIDs = new HashSet<String>();
	
	// 通过单例模式创建TCPServer
	public static TCPServer getInstance() {
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

	public void startAll() throws IOException {
		this.setAllSensorID((ArrayList<String>) HibernateUtil.getSession().createQuery("select s.sensorID from Sensor as s").list());
		if(flag == true) {
			System.out.println("!!!!!!!!!!!!TCPServer开始工作!!!!!!!!!");
			serverSocket = new ServerSocket(7878);
			Socket socket = serverSocket.accept();
			System.out.println("!!!!!!!!!!!!!!收到信息!!!!!!!!!!!!!");
			executor.execute(new DataProcessor(socket));
		}
	}
	
	public void stopAll() throws IOException{
		if(flag == false){
			serverSocket.close();
			this.getAllSensorID().clear();
		}
	}
	
	//TODO
	public void start(ArrayList<String> sensorIDs){
		
	}
	public void stop(){}
	
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
				while((s = br.readLine())!=null){
					//sensorID+"##Temperature##"+data+"##degree";
					System.out.println("!!!!!!!收到的信息"+s);
					String[] receinfo = s.split("##");
					if(TCPServer.getInstance().getAllSensorID().contains(receinfo[0])){
						Sensor sensor = new Sensor();
						Data data = new Data();
						sensor.setSensorID(receinfo[0]);
						sensor.setObservableProperty(receinfo[1]);
						sensor.setUom(receinfo[3]);
						data.setValue(receinfo[2]);
						data.setSensor(sensor);
						isi.insert(data);
					}else{
						System.out.println("This Sensor "+receinfo[0]+" must be registed first!!!");
					}
				}
			}catch(IOException e){
				System.out.println("读取Socket错误！！！");
			}
		}
	}
	


	public ArrayList<String> getAllSensorID() {
		return allSensorID;
	}

	public void setAllSensorID(ArrayList<String> allSensorID) {
		this.allSensorID = allSensorID;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
}
