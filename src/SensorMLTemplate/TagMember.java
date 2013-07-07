package SensorMLTemplate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TagMember {
	@XmlElement(name="sml:System")
	private TagSystem system;
	
	public TagSystem getSystem() {
		return system;
	} 
	
	public void setSystem(TagSystem system) {
		this.system = system;
	}
}
