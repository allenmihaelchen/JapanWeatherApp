package main;
import java.util.Scanner;

import weatharForecastDto.WeatherEntity;
import weatherPrimaryAreaDto.PrimaryArea;


public class Main {

	public static void main(String[] args){
	
		//Basic URL that will concatenate with city number for getting weather forecast JSON.
		String baseURL = "http://weather.livedoor.com/forecast/webservice/json/v1?city=";
		
		Scanner userInput = new Scanner(System.in);
		
		PrimaryAreaDao priObj = new PrimaryAreaDao();
		
		//Get the primary weather area list and city lists from livedoor primary area XML.
		PrimaryArea pri = priObj.fetchData();
		
		//Get quantities of primary weather areas.
		int primaryAreaCount =pri.getChannel().getLdweather().getPref().size();
		
		//Print out the list of primary weather areas. 
		System.out.println("---------------------------Please Enter a primary area number or 0 to exit---------------------------");
		for(int i=0;i<primaryAreaCount;i++){
			System.out.print(String.format("%2d%1s%-6s", i+1,"-",pri.getChannel().getLdweather().getPref().get(i).getTitle()));
			if((i+1)%10==0){
				System.out.println("");
			}
		}
		System.out.println("");
		System.out.println("-------E.g. Enter 2 to look up 2-道東's local weather forecasts, or enter 0 to exit the program-------");
		System.out.println("");
		
		//Initiate the user input validator.
		InputValidator inputValidator = new InputValidator();
		
		//Get the user input of primaryAreaNum
		int primaryAreaNum = inputValidator.Validator(userInput,primaryAreaCount);
				
		
		System.out.println("");
		System.out.println("-------------------------------Please Enter a city number or 0 to exit-------------------------------");
		
		for(int i=0;i<pri.getChannel().getLdweather().getPref().get(primaryAreaNum).getCity().size();i++){
			System.out.print(String.format("%2d%1s%-6s", i+1,"-",pri.getChannel().getLdweather().getPref().get(primaryAreaNum).getCity().get(i).getTitle()));
			if((i+1)%10==0){
				System.out.println("");
			}
		}
		
		System.out.println("");
		System.out.println("-------E.g. Enter 2 to look up 2-北見's local weather forecasts, or enter 0 to exit the program-------");
		System.out.println("");
		
		//Get the city quantities of the primary area selected
		int cityCount = pri.getChannel().getLdweather().getPref().get(primaryAreaNum).getCity().size();
		
		//Get the user input of selectedCityNum
		int selectedCityNum = inputValidator.Validator(userInput,cityCount);
				
		//Close the scanner
		userInput.close();
		
		
				//Get the city code of the city selected
				String selectedCityCode = pri.getChannel().getLdweather().getPref().get(primaryAreaNum).getCity().get(selectedCityNum).getId();
				
				//Concatenate baseURL with city code to get the URL for fetching the JSON 
				String cityURL = baseURL.concat(selectedCityCode);
				
				
				System.out.println("");
				System.out.println("------");
				
				ForecastDao weatherForecastDao = new ForecastDao();
				
				WeatherEntity obj = weatherForecastDao.fetchForecast(cityURL);
				
				System.out.println(obj.getTitle() + " " + obj.getDescription().getPublicTime());
				System.out.println(obj.getDescription().getText());
				
    }
	

}
