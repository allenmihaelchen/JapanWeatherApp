package weatharForecastDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Forecast {
	private String dateLabel;
	private String telop;
	private String date;
	private Temperature temperature;
	
	
	public Temperature getTemperature() {
		return temperature;
	}
	public void setTemperature(Temperature temperature) {
		this.temperature = temperature;
	}
	
	public String getDateLabel() {
		return dateLabel;
	}
	public void setDateLabel(String dateLabel) {
		this.dateLabel = dateLabel;
	}
	public String getTelop() {
		return telop;
	}
	public void setTelop(String telop) {
		this.telop = telop;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	

}
