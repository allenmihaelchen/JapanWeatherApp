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
		
        System.out.println("----------kakakjk");
        System.out.println(pri.getChannel().getLdweather().getPref().get(3).getTitle());
		System.out.println(pri.getChannel().getLdweather().getPref().get(0).getCity().get(2).getTitle());
		System.out.println(pri.getChannel().getLdweather().getPref().get(0).getCity().get(2).getId());
		System.out.println("----------lalaljk");
		
		
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
