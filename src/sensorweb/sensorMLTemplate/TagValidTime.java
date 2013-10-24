package sensorweb.sensorMLTemplate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TagValidTime {
	@XmlElement(name="gml:TimePeriod")
	private TagTimePeriod timePeriod;

	public TagTimePeriod getTimePeriod() {
		return timePeriod;
	}

	public void setTimePeriod(TagTimePeriod timePeriod) {
		this.timePeriod = timePeriod;
	}
	
	
}
