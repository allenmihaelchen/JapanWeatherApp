package main;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import weatherPrimaryAreaDto.PrimaryArea;


public class PrimaryAreaDao {
	
	    // The URL which gets the XML with primary area data in it.
	    String primary_area_XML_URL = "http://weather.livedoor.com/forecast/rss/primary_area.xml";

		public PrimaryArea fetchData(){
			
			CloseableHttpClient httpclient = HttpClients.createDefault();
			PrimaryArea pri = null;
			
			//Establish a http connection with Apache HttpClient 
	        try (CloseableHttpResponse response = httpclient.execute(new HttpGet(primary_area_XML_URL));){
	            
	            JAXBContext jaxbContext = JAXBContext.newInstance(PrimaryArea.class);
	            //Utilize JAXB to do the XML parsing
	    		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	    		
	    		HttpEntity entity = response.getEntity();
	    		//Convert the response to String variable and replace "ldWeather:source" with "source" that contains no colon.
	    		String content = EntityUtils.toString(entity).replace("ldWeather:source", "source");
	    		
	    		// Transform the new String variable into a File object for the unmarshaller and unmarshal the XML to convert it into a POJO.
	    		pri = (PrimaryArea) unmarshaller.unmarshal(new StreamSource(new StringReader(content)));
	    		
	    		//Close the http connection
	    		httpclient.close();
	    		
	        }catch(ClientProtocolException e){
	        	e.printStackTrace();
	        }catch(JAXBException e){
	        	e.printStackTrace();
	        }catch (IOException e) {
				e.printStackTrace();
			} 
			
	        //Return the object with data model PrimaryArea format
			return pri;
		}

}
