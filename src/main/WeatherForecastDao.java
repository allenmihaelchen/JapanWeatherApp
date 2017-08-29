package main;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import weatharForecastDto.WeatherEntity;

public class WeatherForecastDao {
	
	public WeatherEntity fetchForecast(String cityURL){
		
		WeatherEntity obj = null;
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			obj = mapper.readValue(new URL(cityURL), WeatherEntity.class);

			
		}catch(JsonMappingException e){
			e.printStackTrace();
		}catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return obj;
	}

}
