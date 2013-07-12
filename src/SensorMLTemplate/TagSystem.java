package SensorMLTemplate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TagSystem {
	@XmlElement(name="gml:description")
	private String descripition;
	@XmlElement(name="sml:keywords")
	private TagKeyWords keywords;
	@XmlElement(name="sml:identification")
	private TagIdentification identification;
	@XmlElement(name="sml:validTime")
	private TagValidTime validTime;
	@XmlElement(name="sml:capabilities")
	private TagCapabilities capabilites;
	@XmlElement(name="sml:position")
	private TagPosition position;
	@XmlElement(name="sml:input")
	private TagInput input;
	
	
	public TagIdentification getIdentification() {
		return identification;
	}

	public void setIdentification(TagIdentification identification) {
		this.identification = identification;
	}

	public String getDescripition() {
		return descripition;
	}

	public void setDescripition(String descripition) {
		this.descripition = descripition;
	}

	public TagKeyWords getKeywords() {
		return keywords;
	}

	public void setKeywords(TagKeyWords keywords) {
		this.keywords = keywords;
	}

	public TagValidTime getValidTime() {
		return validTime;
	}

	public void setValidTime(TagValidTime validTime) {
		this.validTime = validTime;
	}

	public TagCapabilities getCapabilites() {
		return capabilites;
	}

	public void setCapabilites(TagCapabilities capabilites) {
		this.capabilites = capabilites;
	}

	public TagPosition getPosition() {
		return position;
	}

	public void setPosition(TagPosition position) {
		this.position = position;
	}

	public TagInput getInput() {
		return input;
	}

	public void setInput(TagInput input) {
		this.input = input;
	}
}
