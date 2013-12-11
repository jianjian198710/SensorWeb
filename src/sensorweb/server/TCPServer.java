package sensorweb.server;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import sensorweb.javaBean.Sensor;
import sensorweb.util.MongoUtil;

import com.google.code.morphia.query.Query;

public class TCPServer{
	private static TCPServer instance;
	public static final boolean SENSOR_START = true;
	public static final boolean SENSOR_STOP = false;

	private TCPServer() {}
	
	private ConcurrentHashMap<String,Boolean> sensorStatus = new ConcurrentHashMap<String,Boolean>();
	
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
		Query<Sensor> Sensors = MongoUtil.ds.createQuery(Sensor.class).retrievedFields(true, "sensorID");
		for(Sensor sensor:Sensors.fetch()){
			this.getSensorStatus().put(sensor.getSensorID(),SENSOR_START);
		}
		System.out.println("TCPServer中保存的Sensor列表为: "+this.getSensorStatus());
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
	
	public ConcurrentHashMap<String, Boolean> getSensorStatus() {
		return sensorStatus;
	}

	public void setSensorStatus(ConcurrentHashMap<String, Boolean> sensorStatus) {
		this.sensorStatus = sensorStatus;
	}
}
