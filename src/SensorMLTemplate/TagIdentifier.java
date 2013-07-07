package SensorMLTemplate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TagIdentifier {
	@XmlElement(name="sml:Term")
	private TagTerm term;

	public TagTerm getTerm() {
		return term;
	}

	public void setTerm(TagTerm term) {
		this.term = term;
	}
	
	
}
