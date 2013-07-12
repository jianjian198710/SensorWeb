package SensorMLTemplate;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TagInput {
	@XmlElementWrapper(name="swl:InputList")
	@XmlElement(name="sml:input")
	private List<TagObservableProperty> inputs;
	
	@XmlAttribute(name="name")
	private String name;

	public List<TagObservableProperty> getInputs() {
		return inputs;
	}

	public void setInputs(List<TagObservableProperty> inputs) {
		this.inputs = inputs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
