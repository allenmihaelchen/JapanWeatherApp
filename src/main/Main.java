package main;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import resultOutput.PrintConsole;
import resultOutput.PrintFile;
import weatharForecastDto.WeatherEntity;
import weatherPrimaryAreaDto.PrimaryArea;


public class Main {

	public static void main(String[] args){
	
		//The URL for fetching primary area XML
		String primary_area_XML_URL = "http://weather.livedoor.com/forecast/rss/primary_area.xml";
		//Basic URL that will concatenate with city number for getting weather forecast JSON
		String baseURL = "http://weather.livedoor.com/forecast/webservice/json/v1?city=";
		
		//Launch a scanner for getting the user input
		Scanner userInput = new Scanner(System.in);
		
		PrimaryAreaDao priObj = new PrimaryAreaDao();
		
		//Get the primary weather area list and city lists from livedoor primary area XML
		PrimaryArea pri = priObj.fetchArea(primary_area_XML_URL);
		
		//Get quantities of primary weather areas
		int primaryAreaCount =pri.getChannel().getLdweather().getPref().size();
		
		//Grab an item as an example for the input explanation
		String example = null;
		
		//Print out the list of primary weather areas
		System.out.println("---------------------------Please Enter a primary area number or 0 to exit---------------------------");
		for(int i=0;i<primaryAreaCount;i++){
			System.out.print(String.format("%2d%1s%-6s", i+1,"-",pri.getChannel().getLdweather().getPref().get(i).getTitle()));
			if((i+1)%10==0){
				System.out.println("");
			}
			
			if(i==1){
				example=i+1 + "-" + pri.getChannel().getLdweather().getPref().get(i).getTitle();
			}
		}
		System.out.println("");
		System.out.println("-------E.g. Enter 2 to look up "+ example +"'s local weather forecasts, or enter 0 to exit the program-------");
		System.out.println("");
		
		//Initiate the user input validator
		InputValidator inputValidator = new InputValidator();
		
		//Get the user input of primaryAreaNum and minus 1 to get the correct index
		int primaryAreaNum = inputValidator.Validator(userInput,primaryAreaCount)-1;
				
		
		System.out.println("");
		System.out.println("-------------------------------Please Enter a city number or 0 to exit-------------------------------");
		
		for(int i=0;i<pri.getChannel().getLdweather().getPref().get(primaryAreaNum).getCity().size();i++){
			System.out.print(String.format("%2d%1s%-6s", i+1,"-",pri.getChannel().getLdweather().getPref().get(primaryAreaNum).getCity().get(i).getTitle()));
			if((i+1)%10==0){
				System.out.println("");
			}
			if(i==0){
				example=i+1 + "-" + pri.getChannel().getLdweather().getPref().get(primaryAreaNum).getCity().get(i).getTitle();
			}
		}
		
		System.out.println("");
		System.out.println("-------E.g. Enter 1 to look up "+ example +"'s local weather forecasts, or enter 0 to exit the program------");
		System.out.println("");
		
		//Get the city quantities of the primary area selected
		int cityCount = pri.getChannel().getLdweather().getPref().get(primaryAreaNum).getCity().size();
		
		//Get the user input of selectedCityNum and minus 1 to get the correct index
		int selectedCityNum = inputValidator.Validator(userInput,cityCount)-1;
		
		
		System.out.println("");
		System.out.println("-------------------------------Please Enter the output format-------------------------------");
		System.out.println("-----E.g. Enter 1 to print out in the console or 2 to output as a txt file or 0 to exit-----");
		System.out.println("");
		
		int selectedFormat = inputValidator.Validator(userInput,2);
		
		System.out.println("");
		System.out.println("---");
		

		//Get the city code of the city selected
		String selectedCityCode = pri.getChannel().getLdweather().getPref().get(primaryAreaNum).getCity().get(selectedCityNum).getId();
		
		//Concatenate baseURL with city code to get the URL for fetching the JSON 
		String cityURL = baseURL.concat(selectedCityCode);
		
		ForecastDao weatherForecast = new ForecastDao();
		
		//Get the city weather forecast entity after parsing the JSON
		WeatherEntity obj = weatherForecast.fetchForecast(cityURL);

		
		if(selectedFormat==1){
			//Close this scanner since it wouldn't be used anymore here
			userInput.close();
			
			PrintConsole printConsole = new PrintConsole();
			
			//Print out the weather forecast to the console
			printConsole.printOut(obj);
			
		}else if(selectedFormat==2){
			
			System.out.println("");
			System.out.println("------------------------------Please Enter the output Path------------------------------");
			System.out.println("-------------------E.g. \"/Users/[your user name]/Desktop/\" for Mac OS-------------------");
			System.out.println("--------------------E.g. \"c:\\Users\\[user name]\\Desktop\\\" for Windows--------------------");
			System.out.println("");
			
			String path = inputValidator.PathValidator(userInput);
			
			PrintFile printFile = new PrintFile();
			printFile.printOut(path,obj);
			
			//Close the scanner after getting the user input path and print out the file
			userInput.close();
		}
				
    }

}
