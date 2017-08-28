package main;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import weatharForecastDto.WeatherEntity;
import weatherPrimaryAreaDto.PrimaryArea;


public class Main {

	public static void main(String[] args){
	
		String baseURL = "http://weather.livedoor.com/forecast/webservice/json/v1?city=016010";
		//Basic URL that will concatenate with city number for getting weather forecast JSON.
		
		int primaryAreaNum = 1;
		//Initiate the primaryAreaNum which will be overwrite by the user input later.
		
		@SuppressWarnings("resource")
		Scanner userInput = new Scanner(System.in);
		
		PrimaryAreaDao priObj = new PrimaryAreaDao();
		PrimaryArea pri = priObj.fetchData();
		//Get the primary weather area list and city lists from livedoor primary area XML.
		
		int primaryAreaCount =pri.getChannel().getLdweather().getPref().size();
		//Get quantities of primary weather areas.
		
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
		//Print out the list of primary weather areas. 
		
		
		boolean keepLooping = true;
		//For controlling the loop.
		boolean misFormatWarn = false;
		//For handling different error hints.
		boolean firstLoopCount=true;
		//For detecting whether it's the first loop.
		
		do{
			if(firstLoopCount==false && misFormatWarn==false){
				System.out.println("Please enter an integer number between 1 and "+primaryAreaCount+". Other values are not acceptable!\n");
			}
			keepLooping = false;
			System.out.print("Please enter primary area number here:");
			
			try{
				primaryAreaNum = Integer.parseInt(userInput.next());
				misFormatWarn = false;
			}catch(Exception e){
				System.out.println("Please enter an integer number between 1 and "+primaryAreaCount+". Other format such as String or float is not acceptable!");
				keepLooping = true;
				System.out.println("");
				misFormatWarn=true;
			}
			
			if(primaryAreaNum==0){
				System.out.println("bye bye!");
				System.exit(0);
			}
			//Exit the program if get 0 from the user.
			
			firstLoopCount=false;
			
		}while(keepLooping==true || (primaryAreaNum<=0 || primaryAreaNum>primaryAreaCount));
		//For validating the value user inputed.
		
		
		
	
		
		
		
		

		try {
			ObjectMapper mapper = new ObjectMapper();
			WeatherEntity obj = mapper.readValue(new URL(baseURL), WeatherEntity.class);
			System.out.println(obj.getTitle() + " " + obj.getDescription().getPublicTime());
			System.out.println(obj.getDescription().getText());
			
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


    }
	

}
