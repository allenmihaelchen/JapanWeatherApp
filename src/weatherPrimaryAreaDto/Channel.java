package weatherPrimaryAreaDto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="channel")
public class Channel {

	private LdWeather ldweather;


	@XmlElement(name="source")
	public LdWeather getLdweather() {
		return ldweather;
	}

	public void setLdweather(LdWeather ldweather) {
		this.ldweather = ldweather;
	}
	

}
