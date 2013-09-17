package Service;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import JavaBean.Data;
import JavaBean.Sensor;
import Server.TCPServer;

public class SensorProxy {
	
	TCPServer tcpServer;
	
	private String recinfo = null;
	
	ArrayList<Sensor> sensors = (ArrayList<Sensor>) HibernateUtil.getSession().createQuery("from Sensor").list();
	//Simulated data
//	private static Random rand = new Random(47);
//	public String create(String sensorID){
//		int data = rand.nextInt(50);
//		String s1 = sensorID+"##Temperature##"+data+"##degree";
//		return s1;
//	}
//	
//	private Timer timer = new Timer();
//	private TimerTask task = new TimerTask(){
//		@Override 
//		public void run(){
//			create("Temp1");
//		}
//	};
//	private TimerTask task2 = new TimerTask(){
//		@Override
//		public void run(){
//			create("Temp2");
//		}
//	};
//	public void start(){
//		timer.schedule(task, 0, 3*1000);
//		timer.schedule(task, 0, 5*1000);
//	}
	
	//Process data
	public void insert(String s){
		s = "111"+"##Temperature##"+"1234"+"##degree";
		Sensor sensor = new Sensor();
		Data data = new Data();
		String[] datas = s.split("##");
//		if(HibernateUtil.get(Sensor.class,strs[0])!=null&&!sensorIDs.contains(strs[0])){
//			sensorIDs.add(strs[0]);
//			create(sensor, str[0]);
//		}
		sensor.setSensorID(datas[0]);
		sensor.setObservableProperty(datas[1]);
		sensor.setUom(datas[3]);
		data.setValue(datas[2]);
		data.setSensor(sensor);
		HibernateUtil.add(data);
	}
}
