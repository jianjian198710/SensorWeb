package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
	
	private static final int PORT = 7878;

	private TCPServer() {
		executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*50);
	}
	
//	private ArrayList<String> allSensorID = new ArrayList<String>();
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
	
//	public ServerSocket getServerSocket() throws IOException{
//		if(serverSocket == null){
//			serverSocket = new ServerSocket(PORT);
//		}
//		return serverSocket;
//	}

	public void start() throws IOException {
		System.out.println("执行start方法!!!!!!!");
		List allsensorID =  HibernateUtil.getSession().createQuery("select s.sensorID from Sensor as s").list();
		for(Object sensorID: allsensorID){
			if(sensorID instanceof java.lang.String){
				sensorIDs.add((String)sensorID);
			}
		}
		if(serverSocket==null||serverSocket.isClosed()){
			System.out.println("!!!!!!!!!!!!TcpServer StartAll!!!!!!!!!");
			serverSocket = new ServerSocket(PORT);
			try{
				Socket socket = serverSocket.accept();
				System.out.println("!!!!!!!!!!!!!!收到信息!!!!!!!!!!!!!");
				executor.execute(new DataProcessor(socket));
			}catch(SocketException e){
				System.out.println("serverSocket已经关闭!!!!!");
			}
		}
		System.out.println("Start方法执行完毕!!!!!!!+serverSocket is"+serverSocket.isClosed());
	}
	
	public void stop() throws IOException{
		this.getSensorIDs().clear();
		if(!serverSocket.isClosed()){
			serverSocket.close();
		}
		System.out.println("stop执行完毕!!!!!!!!serverSocket is"+serverSocket.isClosed());
	}
	
	public void startSome(HashSet<String> selectedSensorIDs) throws IOException{
		//如果ServerSocket已关闭 则重新打开 并将sensorIDs的内容设为已经选中的内容
		this.getSensorIDs().addAll(selectedSensorIDs);
		if(serverSocket==null||serverSocket.isClosed()){
			serverSocket = new ServerSocket(PORT);
			try{
				Socket socket = serverSocket.accept();
				System.out.println("!!!!!!!!!!!!!!收到信息!!!!!!!!!!!!!");
				executor.execute(new DataProcessor(socket));
			}catch(SocketException e){
				System.out.println("serverSocket已经关闭!!!!!");
			}
		}
		System.out.println("startSome执行完毕!!!!!!!!serverSocket is"+serverSocket.isClosed());
	}
	
	public void stopSome(HashSet<String> selectedSensorIDs) throws IOException{
		System.out.println("selectedSensorIDs is: "+selectedSensorIDs);
		for(String sensorID: selectedSensorIDs){
			if(this.getSensorIDs().contains(sensorID)){
				this.getSensorIDs().remove(sensorID);
			}
		}
		System.out.println(this.getSensorIDs());
		if(this.getSensorIDs().isEmpty()&&!serverSocket.isClosed()){
			serverSocket.close();
		}
		System.out.println("stopSome执行完毕!!!!!!!serverSocket is"+serverSocket.isClosed());
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
					System.out.println("!!!!!!!收到的信息"+s);
					String[] receinfo = s.split("##");
					if(TCPServer.getInstance().getSensorIDs().contains(receinfo[0])){
						Sensor sensor = new Sensor();
						Data data = new Data();
						sensor.setSensorID(receinfo[0]);
						//sensor.setObservableProperty(receinfo[1]);
						//sensor.setUom(receinfo[3]);
						data.setValue(receinfo[2]);
						data.setSensor(sensor);
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
				System.out.println("读取Socket错误！！！");
			}
		}
	}

	public HashSet<String> getSensorIDs() {
		return sensorIDs;
	}

	public void setSensorIDs(HashSet<String> sensorIDs) {
		this.sensorIDs = sensorIDs;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
}
