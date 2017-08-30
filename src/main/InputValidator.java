package main;

import java.io.File;
import java.util.Scanner;

public class InputValidator {
	
	public int Validator(Scanner scan,int count){
		
		//Initiate the variable which will be overwrote by the user input later
		int inputNum =1;
		
		//For controlling the loop.
		boolean keepLooping = true;
		//For handling different error hints.
		boolean misFormatWarn = false;
		//For detecting whether it's the first loop.
		boolean firstLoopCount=true;
				
		//For validating the value user inputed.
				do{
					if(firstLoopCount==false && misFormatWarn==false){
						System.out.println("Please enter an integer number between 1 and "+count+". Other values are not acceptable!\n");
					}
					keepLooping = false;
					System.out.print("Please enter the number here:");
					
					try{
						inputNum = Integer.parseInt(scan.next());
						misFormatWarn = false;
					}catch(Exception e){
						System.out.println("Please enter an integer number between 1 and "+count+". Other format such as String or float is not acceptable!");
						keepLooping = true;
						System.out.println("");
						misFormatWarn=true;
					}
					
					//Exit the program if get 0 from the user.
					if(inputNum==0){
						System.out.println("bye bye!");
						System.exit(0);
					}
					
					firstLoopCount=false;
					
				}while(keepLooping==true || (inputNum<0 || inputNum>count));
				System.out.println("");
		
		return inputNum;
		
	}
	
	
	public String PathValidator(Scanner scan2){
		
		//For controlling the looping
		boolean keepLooping = true;
		
		//Initiate the inputPath to be overwrote by the user input
		String inputPath="";
		
						do{
							System.out.print("Please enter path for outputting the weather forecase file here:");
							
							File file = new File((inputPath=(String)scan2.next()));
							
							//Validate if the user input is a valid directory path
							if(file.isDirectory()){
								keepLooping = false;
							}else{
								System.out.println("\""+inputPath+"\""+" you entered is not a valid path, please try again.");
								System.out.println("");
								keepLooping = true;
							}
							
						}while(keepLooping == true);
						System.out.println("");
		
		return inputPath;
	}

}
