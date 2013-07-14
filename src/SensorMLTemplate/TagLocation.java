package SensorMLTemplate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TagLocation {
	@XmlElement(name="swe:Vector")
	private TagVector vector;

	public TagVector getVector() {
		return vector;
	}

	public void setVector(TagVector vector) {
		this.vector = vector;
	}
}
