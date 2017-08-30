package resultOutput;

import weatharForecastDto.WeatherEntity;

public class PrintConsole {
	
	public void printOut(WeatherEntity obj){
		System.out.println(obj.getTitle() + " " + obj.getDescription().getPublicTime());
		System.out.println(obj.getDescription().getText());
	}
	
	
}
