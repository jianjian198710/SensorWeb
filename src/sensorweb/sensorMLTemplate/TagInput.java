package sensorweb.sensorMLTemplate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TagInput {
	@XmlElement(name="swe:ObservableProperty")
	private TagObservableProperty observableProperty;

	public TagObservableProperty getObservableProperty() {
		return observableProperty;
	}

	public void setObservableProperty(TagObservableProperty observableProperty) {
		this.observableProperty = observableProperty;
	}
}
