package SensorMLTemplate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TagQuantity {
	@XmlAttribute(name="axisID")
	private String axisID;
	@XmlElement(name="swe:uom")
	private TagUom uom;
	@XmlElement(name="swe:value")
	private String value;
	
	public String getAxisID() {
		return axisID;
	}
	public void setAxisID(String axisID) {
		this.axisID = axisID;
	}
	public TagUom getUom() {
		return uom;
	}
	public void setUom(TagUom uom) {
		this.uom = uom;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
