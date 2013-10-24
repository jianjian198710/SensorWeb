package sensorweb.sensorMLTemplate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TagField {
	@XmlAttribute(name="name")
	private String name;
	@XmlElement(name="swe:Time")
	private TagTime time;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public TagTime getTime() {
		return time;
	}

	public void setTime(TagTime time) {
		this.time = time;
	}
}
