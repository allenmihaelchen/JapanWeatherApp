package weatherDataModel;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="city")
public class City {
	private String title;
	
	private String id;
	
	public String getTitle() {
		return title;
	}
	
	@XmlAttribute(name="title")
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getId() {
		return id;
	}
	
	@XmlAttribute(name="id")
	public void setId(String id) {
		this.id = id;
	}
	

}
