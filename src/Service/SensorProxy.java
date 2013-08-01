package Service;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import JavaBean.Sensor;

public class SensorProxy {
	
	ArrayList<Sensor> sensors = (ArrayList<Sensor>) HibernateUtil.getSession().createQuery("from Sensor").list();
	//Simulated data
	private static Random rand = new Random(47);
	public String create(String sensorID){
		int data = rand.nextInt(50);
		String s1 = sensorID+"##Temperature##"+data+"##degree";
		return s1;
	}
	
	private Timer timer = new Timer();
	private TimerTask task = new TimerTask(){
		@Override 
		public void run(){
			create("Temp1");
		}
	};
	private TimerTask task2 = new TimerTask(){
		@Override
		public void run(){
			create("Temp2");
		}
	};
	public void start(){
		timer.schedule(task, 0, 3*1000);
		timer.schedule(task, 0, 5*1000);
	}
	
	//Process data
	public void insert(String s){
		Sensor sensor = null;
		String[] strs = s.split("##");
		if(HibernateUtil.get(Sensor.class,strs[0])!=null&&!sensorIDs.contains(strs[0])){
			sensorIDs.add(strs[0]);
			create(sensor, str[0]);
		}
		sensor.setObservableProperty(strs[1]);
		sensor.setData(strs[2]);
		sensor.setUom(strs[3]);
		HibernateUtil.add(sensor);
	}
	
	public Sensor create(Sensor sensor,String s){
		if(sensor==null){
			sensor = new Sensor(s);
			return sensor;
		}else{
			return sensor;
		}
	}
	
}
