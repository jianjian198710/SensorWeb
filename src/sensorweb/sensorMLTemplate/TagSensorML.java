package sensorweb.sensorMLTemplate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="swe:SensorML")
@XmlAccessorType(XmlAccessType.FIELD)
public class TagSensorML {
	@XmlElement(name="sml:member")
	private TagMember member;

	public TagMember getMember() {
		return member;
	}

	public void setMember(TagMember member) {
		this.member = member;
	}
}
