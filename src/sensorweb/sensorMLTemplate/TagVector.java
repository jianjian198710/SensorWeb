package sensorweb.sensorMLTemplate;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TagVector {
	@XmlAttribute(name="gml:id")
	private String gmlid;
	
	@XmlElement(name="swe:coordinate")
    private List<TagCoordinate> coordinates;
	
	public String getGmlid() {
		return gmlid;
	}
	public void setGmlid(String gmlid) {
		this.gmlid = gmlid;
	}
	public List<TagCoordinate> getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(List<TagCoordinate> coordinates) {
		this.coordinates = coordinates;
	}
}
