package sensorweb.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import sensorweb.javaBean.Data;
import sensorweb.javaBean.Sensor;
import sensorweb.service.InsertServiceImp;
import sensorweb.util.MongoUtil;

import com.google.code.morphia.query.Query;



public class TCPServer{
	private boolean flag;
	private static TCPServer instance;
	private ServerSocket serverSocket = null;
	private ExecutorService executor;
	
	private static final int PORT = 7878;
	private static final boolean SENSOR_START = true;
	private static final boolean SENSOR_STOP = false;

	private TCPServer() {
		executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*50);
	}
	
	private HashMap<String,Boolean> sensorStatus = new HashMap<String,Boolean>();
	
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
		System.out.println("执行startAll方法!!!!!!!");
		
		Query<Sensor> Sensors = MongoUtil.ds.createQuery(Sensor.class).retrievedFields(true, "sensorID");
		for(Sensor sensor:Sensors.fetch()){
			this.getSensorStatus().put(sensor.getSensorID(),SENSOR_START);
		}
		
		System.out.println("TCPServer中保存的Sensor列表为: "+this.getSensorStatus());
		if(serverSocket==null){
			System.out.println("启动ServerSocket!!!");
			serverSocket = new ServerSocket(PORT);
			//这个循环很关键
			while(true){
				Socket socket = serverSocket.accept();
				executor.execute(new DataProcessor(socket));
			}
		}
	}
	
	public void stopAll() throws IOException{
		for(Map.Entry<String, Boolean> entry: this.getSensorStatus().entrySet()){
			String key = entry.getKey();
			this.sensorStatus.put(key, SENSOR_STOP);
		}
		System.out.println("TCPServer中保存的Sensor列表为: "+this.getSensorStatus());
	}
	
	public void startSome(HashSet<String> selectedSensorIDs) throws IOException{
		//将sensorIDs的内容设为已经选中的内容
		if(selectedSensorIDs.isEmpty()){
			return;
		}else{
			System.out.println("selectedSensorIDs is: "+selectedSensorIDs);
			for(String selectedSensorID:selectedSensorIDs){
				this.getSensorStatus().put(selectedSensorID, SENSOR_START);
			}
			Query<Sensor> Sensors = MongoUtil.ds.createQuery(Sensor.class).retrievedFields(true, "sensorID");
			for(Sensor sensor:Sensors.fetch()){
				if(!this.getSensorStatus().containsKey(sensor.getSensorID())){
					this.getSensorStatus().put(sensor.getSensorID(),SENSOR_STOP);
				}
			}
		}
		System.out.println("TCPServer中保存的Sensor列表为: "+this.getSensorStatus());
		if(serverSocket==null){
			System.out.println("启动ServerSocket!!!");
			serverSocket = new ServerSocket(PORT);
			while(true){
				Socket socket = serverSocket.accept();
				executor.execute(new DataProcessor(socket));
			}
		}
	}
	
	public void stopSome(HashSet<String> selectedSensorIDs) throws IOException{
		if(selectedSensorIDs.isEmpty()){
			return;
		}else{
			System.out.println("selectedSensorIDs is: "+selectedSensorIDs);
			for(String selectedSensorID: selectedSensorIDs){
				if(this.getSensorStatus().containsKey(selectedSensorID)){
					this.getSensorStatus().put(selectedSensorID, SENSOR_STOP);
				}
			}
		}
		System.out.println("TCPServer中保存的Sensor列表为: "+this.getSensorStatus());
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
							TCPServer.getInstance().getSensorStatus().get(receinfo[0])==SENSOR_START){
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
				System.out.println("读取Socket错误！！！");
			}
		}
	}
	
	public HashMap<String, Boolean> getSensorStatus() {
		return sensorStatus;
	}

	public void setSensorStatus(HashMap<String, Boolean> sensorStatus) {
		this.sensorStatus = sensorStatus;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public ServerSocket getServerSocket(){
		return serverSocket;
	}
}
