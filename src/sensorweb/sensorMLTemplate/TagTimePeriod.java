package sensorweb.sensorMLTemplate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TagTimePeriod {
	@XmlElement(name="gml:beginPosition")
	private String beginPosition;
	@XmlElement(name="gml:endPosition")
	private String endPosition;
	
	public String getBeginPosition() {
		return beginPosition;
	}
	public void setBeginPosition(String beginPosition) {
		this.beginPosition = beginPosition;
	}
	public String getEndPosition() {
		return endPosition;
	}
	public void setEndPosition(String endPosition) {
		this.endPosition = endPosition;
	}
}
