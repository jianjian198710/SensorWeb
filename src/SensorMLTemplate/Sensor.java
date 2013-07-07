package SensorMLTemplate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="sml:SensorML")
@XmlAccessorType(XmlAccessType.FIELD)
public class Sensor {
	@XmlElement(name="sml:member")
	private TagMember member;

	public TagMember getMember() {
		return member;
	}

	public void setMember(TagMember member) {
		this.member = member;
	}
}
