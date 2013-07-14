package SensorMLTemplate;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TagInputs {
	@XmlElementWrapper(name="swl:InputList")
	@XmlElement(name="sml:input")
	private List<TagInput> input;

	public List<TagInput> getInput() {
		return input;
	}

	public void setInput(List<TagInput> input) {
		this.input = input;
	}
}
