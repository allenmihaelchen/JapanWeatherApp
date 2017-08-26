package weatherDataModel;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class WeatherEntity {
	private List<PinPointLocation> pinpointLocations;
	private List<Forecast> forecasts;
	private String link;
	private Location location;
	
	private String publicTime;
	
	@JsonIgnoreProperties(ignoreUnknown=true)
	private Copyright copyright;
	private String title;
	private Description description;
	
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public Copyright getCopyright() {
		return copyright;
	}
	public void setCopyright(Copyright copyright) {
		this.copyright = copyright;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Description getDescription() {
		return description;
	}
	public void setDescription(Description description) {
		this.description = description;
	}
	public List<PinPointLocation> getPinpointLocations() {
		return pinpointLocations;
	}
	public void setPinpointLocations(List<PinPointLocation> pinpointLocations) {
		this.pinpointLocations = pinpointLocations;
	}
	public List<Forecast> getForecasts() {
		return forecasts;
	}
	public void setForecasts(List<Forecast> forecasts) {
		this.forecasts = forecasts;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getPublicTime() {
		return publicTime;
	}
	public void setPublicTime(String publicTime) {
		this.publicTime = publicTime;
	}

}
