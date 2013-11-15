package sensorweb.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
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

	private TCPServer() {
		executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*50);
	}
	
//	private ArrayList<String> allSensorID = new ArrayList<String>();
	private HashSet<String> sensorIDs = new HashSet<String>();
	
	// ͨ������ģʽ����TCPServer
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

	public void start() throws IOException {
		System.out.println("ִ��start����!!!!!!!");
		
		Query<Sensor> Sensors = MongoUtil.ds.createQuery(Sensor.class).retrievedFields(true, "sensorID");
		for(Sensor sensor:Sensors.fetch()){
			this.getSensorIDs().add(sensor.getSensorID());
		}
		
		System.out.println("TCPServer�б����Sensor�б�Ϊ: "+this.getSensorIDs());
		if(serverSocket==null){
			System.out.println("����ServerSocket!!!");
			serverSocket = new ServerSocket(PORT);
			//���ѭ���ܹؼ�
			while(true){
				Socket socket = serverSocket.accept();
				executor.execute(new DataProcessor(socket));
			}

		}
	}
	
	public void stop() throws IOException{
		this.getSensorIDs().clear();
		System.out.println("TCPServer�б����Sensor�б�Ϊ: "+this.getSensorIDs());
	}
	
	public void startSome(HashSet<String> selectedSensorIDs) throws IOException{
		//��sensorIDs��������Ϊ�Ѿ�ѡ�е�����
		if(selectedSensorIDs.isEmpty()){
			return;
		}else{
			System.out.println("selectedSensorIDs is: "+selectedSensorIDs);
			this.getSensorIDs().addAll(selectedSensorIDs);
		}
		System.out.println("TCPServer�б����Sensor�б�Ϊ: "+this.getSensorIDs());
		if(serverSocket==null){
			System.out.println("����ServerSocket!!!");
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
			for(String sensorID: selectedSensorIDs){
				if(this.getSensorIDs().contains(sensorID)){
					this.getSensorIDs().remove(sensorID);
				}
			}
		}
		System.out.println("TCPServer�б����Sensor�б�Ϊ: "+this.getSensorIDs());
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
					if(TCPServer.getInstance().getSensorIDs().contains(receinfo[0])){
						System.out.println("!!!!!!!�յ�������Ϣ"+s);
						Sensor sensor = new Sensor();
						Data data = new Data();
						sensor.setSensorID(receinfo[0]);
						data.setValue(receinfo[2]);
						data.setSensor(sensor);
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

	public ServerSocket getServerSocket(){
		return serverSocket;
	}
}
