package main;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import weatharForecastDto.WeatherEntity;
import weatherPrimaryAreaDto.PrimaryArea;


public class Main {

	public static void main(String[] args) throws JsonParseException{
		
	
		String baseURL = "http://weather.livedoor.com/forecast/webservice/json/v1?city=016010";
		
		
		PrimaryRegion priObj = new PrimaryRegion();
		PrimaryArea pri = priObj.fetchData();
		
		System.out.println("-------------------------Please Enter a primary area number or 0 to exit-------------------------");
		for(int i=0;i<pri.getChannel().getLdweather().getPref().size();i++){
			System.out.print(String.format("%2d%1s%-6s", i+1,"-",pri.getChannel().getLdweather().getPref().get(i).getTitle()));
//			System.out.print(String.format("%-8s", "-"+pri.getChannel().getLdweather().getPref().get(i).getTitle()));
			if((i+1)%10==0){
				System.out.println("");
			}
		}
		System.out.println("");
		System.out.println("-------E.g. Enter 2 to look up 2-道東's local weather forecasts, Enter 0 (exit the program)-------");
		System.out.println("");
		
		
//        System.out.println(pri.getChannel().getLdweather().getPref().get(3).getTitle());
//		System.out.println(pri.getChannel().getLdweather().getPref().get(0).getCity().get(2).getTitle());
//		System.out.println(pri.getChannel().getLdweather().getPref().get(0).getCity().get(2).getId());
		
		
//		List<Pref> areas = pri.getChannel().getPref();
		
		//System.out.println(pri.getChannel().getPref().get(0).getTitle());
		
//		for(int i=0; i<areas.size();i++){
//			System.out.print(areas.get(i).getTitle()+ " ");
//		}
		
		//System.out.println(pri.getChannel().getLdWeather());
		
		

		try {
			ObjectMapper mapper = new ObjectMapper();
			WeatherEntity obj = mapper.readValue(new URL(baseURL), WeatherEntity.class);
			System.out.println(obj.getTitle() + " " + obj.getDescription().getPublicTime());
			System.out.println(obj.getDescription().getText());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    }
	

}
