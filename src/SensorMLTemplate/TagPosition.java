package SensorMLTemplate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TagPosition {
	@XmlAttribute(name="name")
	private String name;
	@XmlElement(name="swe:Position")
	private TagswePosition position;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TagswePosition getPosition() {
		return position;
	}
	public void setPosition(TagswePosition position) {
		this.position = position;
	}
}
