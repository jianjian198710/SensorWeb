package sensorweb.sensorMLTemplate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TagswePosition {
	@XmlAttribute(name="referenceFrame")
	private String referenceFrame;
	@XmlElement(name="swe:location")
	private TagLocation location;
	
	public String getReferenceFrame() {
		return referenceFrame;
	}
	public void setReferenceFrame(String referenceFrame) {
		this.referenceFrame = referenceFrame;
	}
	public TagLocation getLocation() {
		return location;
	}
	public void setLocation(TagLocation location) {
		this.location = location;
	}
}
