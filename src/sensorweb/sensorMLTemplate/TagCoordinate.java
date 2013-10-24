package sensorweb.sensorMLTemplate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TagCoordinate {
	@XmlAttribute(name="name")
	private String name;
	@XmlElement(name="swe:Quantity")
	private TagQuantity quantity;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TagQuantity getQuantity() {
		return quantity;
	}
	public void setQuantity(TagQuantity quantity) {
		this.quantity = quantity;
	}
}
