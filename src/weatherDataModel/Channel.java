package weatherDataModel;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="channel")
public class Channel {
	private String title;
	private String author;
	private List<SourceEntity> ldWeather ;
	
	public String getTitle() {
		return title;
	}
	
	@XmlElement(name="title")
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	@XmlElement(name="author")
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public List<SourceEntity> getLdWeather() {
		return ldWeather;
	}
	
	@XmlElement(name="ldWeather:source")
	public void setLdWeather(List<SourceEntity> ldWeather) {
		this.ldWeather = ldWeather;
	}

}
