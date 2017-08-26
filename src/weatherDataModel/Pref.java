package weatherDataModel;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="pref")
public class Pref {
	private String title;
	
	private List<City> city;
	
	public String getTitle() {
		return title;
	}
	
	@XmlAttribute(name="title")
	public void setTitle(String title) {
		this.title = title;
	}
	
	public List<City> getCity() {
		return city;
	}
	
	@XmlElement(name="city")
	public void setCity(List<City> city) {
		this.city = city;
	}

}
