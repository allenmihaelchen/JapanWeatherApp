package main;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import weatherDataModel.PrimaryArea;
import weatherDataModel.WeatherEntity;


public class main {

	public final static void main(String[] args) throws JsonParseException, JsonMappingException, IOException, JAXBException {
		
	
		String baseURL = "http://weather.livedoor.com/forecast/webservice/json/v1?city=016010";
		String baseURL2 = "http://weather.livedoor.com/forecast/rss/primary_area.xml";
		
		ObjectMapper mapper = new ObjectMapper();

//		String jsonInString = "{\"name\" : \"lalala\"}";
		//JSON from file to Object
//		Staff obj = mapper.readValue(new File("c:\\file.json"), Staff.class);

		//JSON from URL to Object
		WeatherEntity obj = mapper.readValue(new URL(baseURL), WeatherEntity.class);

		//JSON from String to Object
//		WeatherEntity obj = mapper.readValue(jsonInString, Staff.class);
		
		JAXBContext jaxbContext = JAXBContext.newInstance(PrimaryArea.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		PrimaryArea pri = (PrimaryArea) unmarshaller.unmarshal(new URL(baseURL2));
		
		
		System.out.println("----------lala");
		System.out.println(pri.getChannel().getTitle());
		System.out.println(pri.getChannel().getAuthor());
		System.out.println("----------haha");
		System.out.println(obj.getTitle() + " " + obj.getDescription().getPublicTime());
		System.out.println(obj.getDescription().getText());
		

		
		
//		
//		CloseableHttpClient httpclient = HttpClients.createDefault();
//        try {
//            HttpGet httpget = new HttpGet(baseURL);
//            //Test Comments
//
//            System.out.println("Executing request " + httpget.getRequestLine());
//            CloseableHttpResponse response = httpclient.execute(httpget);
//            BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
//            
//            System.out.println("----------------------------------------");
//            System.out.println(response.getStatusLine());
//            
//            System.out.println("----------------------------------------");
//            String output;
//    		System.out.println("Output from Server .... \n");
//    		while ((output = br.readLine()) != null) {
//    			System.out.println(output);
//    		}
//            
//        }catch(ClientProtocolException e){
//        	e.printStackTrace();
//        }catch(IOException e){
//        	e.printStackTrace();
//        } 
//        finally {
//            httpclient.close();
//        }
    }

		
		
		
//		CloseableHttpClient httpclient = HttpClients.createDefault();
//		HttpGet httpGet = new HttpGet(baseURL);
//		CloseableHttpResponse response1;
//		try {
//			response1 = httpclient.execute(httpGet);
//			System.out.println(response1.getStatusLine());
//		    HttpEntity entity1 = response1.getEntity();
//		    EntityUtils.consume(entity1);
//		    response1.close();
//		} catch (ClientProtocolException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
	

	}
