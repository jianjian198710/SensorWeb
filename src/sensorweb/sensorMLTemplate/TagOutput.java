package sensorweb.sensorMLTemplate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TagOutput {
	@XmlAttribute(name="name")
	private String name;
	
	@XmlElement(name="swe:uom")
	private TagUom uom;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TagUom getUom() {
		return uom;
	}

	public void setUom(TagUom uom) {
		this.uom = uom;
	}
}
