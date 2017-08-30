package resultOutput;

import weatharForecastDto.WeatherEntity;

public class PrintConsole {
	
	public void printOut(WeatherEntity obj){
		System.out.println(obj.getLocation().getArea()+" "+obj.getTitle() + " " + obj.getDescription().getPublicTime());
		System.out.println("Source: "+obj.getLink()+"; "+"From: "+obj.getCopyright().getProvider().get(0).getName()+" "+obj.getCopyright().getProvider().get(0).getLink());
		System.out.println("==============================================");
		
		for(int i=0;i<obj.getForecasts().size();i++){
			System.out.println(obj.getForecasts().get(i).getDate() +" "+obj.getForecasts().get(i).getDateLabel()+" "+obj.getForecasts().get(i).getTelop());
		}
		
		System.out.println();
		System.out.println("Weather Description");
		System.out.println("----------------------------------------------");
		System.out.println(obj.getDescription().getText());
		
		System.out.println();
		System.out.println();
		System.out.println("The weather of other related places");
		System.out.println("----------------------------------------------");
		
		for(int i=0;i<obj.getPinpointLocations().size();i++){
			System.out.println(obj.getPinpointLocations().get(i).getName());
			System.out.println(obj.getPinpointLocations().get(i).getLink());
			System.out.println();	
		}
		
	}
	
	
}
