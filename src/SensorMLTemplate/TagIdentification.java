package SensorMLTemplate;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TagIdentification {
	@XmlElementWrapper(name="sml:IdentifierList")
    @XmlElement(name="sml:identifier")
	private List<TagIdentifier> identifiers;

	public List<TagIdentifier> getIdentifiers() {
		return identifiers;
	}

	public void setIdentifiers(List<TagIdentifier> identifiers) {
		this.identifiers = identifiers;
	}
	
	
}
