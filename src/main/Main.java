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
		
		
		//Initiate the primaryAreaNum which will be overwrite by the user input later.
		int primaryAreaNum = 1;
		
		//For controlling the loop.
		boolean keepLooping = true;
		//For handling different error hints.
		boolean misFormatWarn = false;
		//For detecting whether it's the first loop.
		boolean firstLoopCount=true;
		
		//For validating the value user inputed.
		do{
			if(firstLoopCount==false && misFormatWarn==false){
				System.out.println("Please enter an integer number between 1 and "+primaryAreaCount+". Other values are not acceptable!\n");
			}
			keepLooping = false;
			System.out.print("Please enter the primary area number here:");
			
			try{
				primaryAreaNum = Integer.parseInt(userInput.next());
				misFormatWarn = false;
			}catch(Exception e){
				System.out.println("Please enter an integer number between 1 and "+primaryAreaCount+". Other format such as String or float is not acceptable!");
				keepLooping = true;
				System.out.println("");
				misFormatWarn=true;
			}
			
			//Exit the program if get 0 from the user.
			if(primaryAreaNum==0){
				System.out.println("bye bye!");
				System.exit(0);
			}
			
			firstLoopCount=false;
			
		}while(keepLooping==true || (primaryAreaNum<0 || primaryAreaNum>primaryAreaCount));
		
		//Get back to the original primary area index.
		primaryAreaNum = primaryAreaNum-1;

		
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
		
		
		        //Initiate the selectedCityNum which will be overwrite by the user input later.
				int selectedCityNum = 1;
				
				//Get the quantities of cities in the list
				int cityCount = pri.getChannel().getLdweather().getPref().get(primaryAreaNum).getCity().size();
				
				//For controlling the loop.
				boolean keepLooping2 = true;
				//For handling different error hints.
				boolean misFormatWarn2 = false;
				//For detecting whether it's the first loop.
				boolean firstLoopCount2=true;
				
				//For validating the value user inputed.
				do{
					if(firstLoopCount2==false && misFormatWarn2==false){
						System.out.println("Please enter an integer number between 1 and "+cityCount+". Other values are not acceptable!\n");
					}
					keepLooping2 = false;
					System.out.print("Please enter the city number here:");
					
					try{
						selectedCityNum = Integer.parseInt(userInput.next());
						misFormatWarn2 = false;
					}catch(Exception e){
						System.out.println("Please enter an integer number between 1 and "+cityCount+". Other format such as String or float is not acceptable!");
						keepLooping2 = true;
						System.out.println("");
						misFormatWarn2=true;
					}
					
					//Exit the program if get 0 from the user.
					if(selectedCityNum==0){
						System.out.println("bye bye!");
						System.exit(0);
					}
					
					firstLoopCount2=false;
					
				}while(keepLooping2==true || (selectedCityNum<0 || selectedCityNum>cityCount));
				System.out.println("");
				
				//Close the Scanner
				userInput.close();
				
				//Get back to the original city index.
				selectedCityNum = selectedCityNum-1;
				
				//Get the city code of the city selected
				String selectedCityCode = pri.getChannel().getLdweather().getPref().get(primaryAreaNum).getCity().get(selectedCityNum).getId();
				
				String cityURL = baseURL.concat(selectedCityCode);
				
				System.out.println("The city URL is "+ cityURL);
				
				
				System.out.println("");
				System.out.println("------");
				
				WeatherForecastDao weatherForecastDao = new WeatherForecastDao();
				
				WeatherEntity obj = weatherForecastDao.fetchForecast(cityURL);
				
				System.out.println(obj.getTitle() + " " + obj.getDescription().getPublicTime());
				System.out.println(obj.getDescription().getText());
				
    }
	

}
