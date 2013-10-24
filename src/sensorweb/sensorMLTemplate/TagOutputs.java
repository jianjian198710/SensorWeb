package sensorweb.sensorMLTemplate;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TagOutputs {
	@XmlElementWrapper(name="swl:OutputList")
	@XmlElement(name="sml:output")
	private List<TagOutput> outputs;

	public List<TagOutput> getOutputs() {
		return outputs;
	}

	public void setOutputs(List<TagOutput> outputs) {
		this.outputs = outputs;
	}
}
