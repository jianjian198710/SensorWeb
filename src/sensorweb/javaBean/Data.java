package sensorweb.javaBean;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Reference;


@Entity("datas")
public class Data {
	@Id
	ObjectId id;
	@Reference(lazy=true)
	private Sensor sensor;
	private String value;
	private String timeStamp;
	private String observableProperty;
	private String uom;

	public Sensor getSensor() {
		return sensor;
	}
	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
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
