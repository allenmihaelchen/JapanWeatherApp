package resultOutput;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import weatharForecastDto.WeatherEntity;

public class PrintFile {
	
	public void printOut(String path, WeatherEntity obj){
		
		//Get the prefecture, area and city value as the file name
		String defaultOutputFileName=obj.getLocation().getArea()+"-"+obj.getLocation().getPrefecture()+"-"+obj.getLocation().getCity()+"-"+"WeatherForecast.txt";
		
		path = path.concat(defaultOutputFileName);
		
		File file = new File(path);
		
		System.out.println("The file name to be outputed: "+defaultOutputFileName);
		
		if(file.exists()){
			System.out.println("The existing file with the same name will be deleted before the new file output...");
			file.delete();
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			System.out.println("Creating a new file...");
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
				
		try ( PrintWriter out = new PrintWriter( file ) ){	
			
			out.println(obj.getLocation().getArea()+" "+obj.getTitle() + " " + obj.getDescription().getPublicTime());
			out.println("Source: "+obj.getLink()+"; "+"From: "+obj.getCopyright().getProvider().get(0).getName()+" "+obj.getCopyright().getProvider().get(0).getLink());
			out.println("==============================================");
			
			for(int i=0;i<obj.getForecasts().size();i++){
				out.println(obj.getForecasts().get(i).getDate() +" "+obj.getForecasts().get(i).getDateLabel()+" "+obj.getForecasts().get(i).getTelop());
			}
			
			out.println();
			out.println("Weather Description");
			out.println("----------------------------------------------");
			out.println(obj.getDescription().getText());
			
			out.println();
			out.println();
			out.println("The weather of other related places");
			out.println("----------------------------------------------");
			
			for(int i=0;i<obj.getPinpointLocations().size();i++){
				out.println(obj.getPinpointLocations().get(i).getName());
				out.println(obj.getPinpointLocations().get(i).getLink());
				out.println();	
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(file.exists()){
			System.out.println("File: "+defaultOutputFileName+" was outputted successfully!");
			System.out.println();
			System.out.println("Please check the outputted file in the following directory: ");
			System.out.println(path);
		}else{
			System.out.println("File output failed!");
		}
		
	}

}
