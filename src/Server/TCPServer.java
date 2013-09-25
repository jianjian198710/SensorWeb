package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
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
	
//	public ServerSocket getServerSocket() throws IOException{
//		if(serverSocket == null){
//			serverSocket = new ServerSocket(PORT);
//		}
//		return serverSocket;
//	}

	public void start() throws IOException {
		System.out.println("ִ��start����!!!!!!!");
		List allsensorID =  HibernateUtil.getSession().createQuery("select s.sensorID from Sensor as s").list();
		for(Object sensorID: allsensorID){
			if(sensorID instanceof java.lang.String){
				sensorIDs.add((String)sensorID);
			}
		}
		if(serverSocket==null||serverSocket.isClosed()){
			System.out.println("!!!!!!!!!!!!TcpServer StartAll!!!!!!!!!");
			serverSocket = new ServerSocket(PORT);
			Socket socket = serverSocket.accept();
			System.out.println("!!!!!!!!!!!!!!�յ���Ϣ!!!!!!!!!!!!!");
			executor.execute(new DataProcessor(socket));
		}
	}
	
	public void stop() throws IOException{
		this.getSensorIDs().clear();
		serverSocket.close();
		System.out.println("stopSomeִ�����!!!!!!!!");
	}
	
	public void startSome(HashSet<String> selectedSensorIDs) throws IOException{
		//���ServerSocket�ѹر� �����´� ����sensorIDs��������Ϊ�Ѿ�ѡ�е�����
		this.getSensorIDs().addAll(selectedSensorIDs);
		if(serverSocket==null||serverSocket.isClosed()){
			serverSocket = new ServerSocket(PORT);
			Socket socket = serverSocket.accept();
			System.out.println("!!!!!!!!!!!!!!�յ���Ϣ!!!!!!!!!!!!!");
			executor.execute(new DataProcessor(socket));
		}
		System.out.println("startSomeִ�����!!!!!!!!");
	}
	
	public void stopSome(HashSet<String> selectedSensorIDs) throws IOException{
		this.getSensorIDs().removeAll(selectedSensorIDs);
		if(this.getSensorIDs().isEmpty()&&!serverSocket.isClosed()){
			serverSocket.close();
		}
		System.out.println("stopSomeִ�����!!!!!!!!");
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
				while((s = br.readLine())!=null){
					//sensorID+"##Temperature##"+data+"##degree";
					System.out.println("!!!!!!!�յ�����Ϣ"+s);
					String[] receinfo = s.split("##");
					if(TCPServer.getInstance().getSensorIDs().contains(receinfo[0])){
						Sensor sensor = new Sensor();
						Data data = new Data();
						sensor.setSensorID(receinfo[0]);
						sensor.setObservableProperty(receinfo[1]);
						sensor.setUom(receinfo[3]);
						data.setValue(receinfo[2]);
						data.setSensor(sensor);
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
}
