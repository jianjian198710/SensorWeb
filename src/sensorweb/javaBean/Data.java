package sensorweb.javaBean;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;


@Entity("datas")
public class Data {
	@Id
	ObjectId id;

	private String sensorID;
	private String value;
	private String timeStamp;
	private String observableProperty;
	private String uom;
	
	public String getSensorID() {
		return sensorID;
	}
	public void setSensorID(String sensorID) {
		this.sensorID = sensorID;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
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
