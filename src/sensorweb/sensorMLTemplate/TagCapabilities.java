package sensorweb.sensorMLTemplate;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TagCapabilities {
	@XmlElementWrapper(name="swe:DataRecord")
	@XmlElement(name="swe:field")
	private List<TagField> fields;

	public List<TagField> getFields() {
		return fields;
	}

	public void setFields(List<TagField> fields) {
		this.fields = fields;
	}
}
