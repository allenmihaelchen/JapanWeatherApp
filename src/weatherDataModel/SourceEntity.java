package weatherDataModel;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ldWeather:source")
public class SourceEntity {
	private String title;
	private List<Pref> pref;
	
	
	public String getTitle() {
		return title;
	}
	
	@XmlAttribute(name="title")
	public void setTitle(String title) {
		this.title = title;
	}
	
	public List<Pref> getPref() {
		return pref;
	}
	
	@XmlElement(name="pref")
	public void setPref(List<Pref> pref) {
		this.pref = pref;
	}

}
