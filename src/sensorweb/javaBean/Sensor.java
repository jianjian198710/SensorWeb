package sensorweb.javaBean;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;



@Entity("sensors")
public class Sensor {
	//注意 sensorID由于做主键在数据库保存名为"_id"
	@Id
	private String sensorID;
	private String description;
	private String keyword;
	private String beginTime;
	private String endTime;
	private String samplingTime;
	private String easting;
	private String northing;
	private String altitude;
	private String observableProperty;
	private String uom;
	
	public Sensor(){};
	
	public Sensor(String SensorID){
		this.sensorID = SensorID;
	}
	
	public String toString(){
		return sensorID;
	}
	
	public String getSensorID() {
		return sensorID;
	}
	public void setSensorID(String sensorID) {
		this.sensorID = sensorID;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getSamplingTime() {
		return samplingTime;
	}
	public void setSamplingTime(String samplingTime) {
		this.samplingTime = samplingTime;
	}
	public String getEasting() {
		return easting;
	}
	public void setEasting(String easting) {
		this.easting = easting;
	}
	public String getNorthing() {
		return northing;
	}
	public void setNorthing(String northing) {
		this.northing = northing;
	}
	public String getAltitude() {
		return altitude;
	}
	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}
	public String getObservableProperty() {
		return observableProperty;
	}
	public void setObservableProperty(String observableProperty) {
		this.observableProperty = observableProperty;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
}
