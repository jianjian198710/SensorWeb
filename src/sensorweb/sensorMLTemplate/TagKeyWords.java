package sensorweb.sensorMLTemplate;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="sml:keywords")
@XmlAccessorType(XmlAccessType.FIELD)
public class TagKeyWords {

	@XmlElementWrapper(name="sml:KeywordList" )
    @XmlElement(name="sml:keyword")
    private List<String> keywords;

	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}
}
