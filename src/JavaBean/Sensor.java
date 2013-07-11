package JavaBean;

public class Sensor {
	//Show in SensorML
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
	private String data;
	
	//show whether the senor is start
	private boolean start;
	
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
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public boolean isStart() {
		return start;
	}
	public void setStart(boolean start) {
		this.start = start;
	}
}
