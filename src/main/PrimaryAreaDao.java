package main;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
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
	
	    String primary_area_XML_URL = "http://weather.livedoor.com/forecast/rss/primary_area.xml";
	    // The URL which gets the XML with primary area data in it.

		public PrimaryArea fetchData(){
			
			CloseableHttpClient httpclient = HttpClients.createDefault();
			PrimaryArea pri = null;
			
	        try (CloseableHttpResponse response = httpclient.execute(new HttpGet(primary_area_XML_URL));){
	        	//Establish a http connection with Apache HttpClient 
	            
	            JAXBContext jaxbContext = JAXBContext.newInstance(PrimaryArea.class);
	    		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	    		//Utilize JAXB to do the XML parsing
	    		
	    		HttpEntity entity = response.getEntity();
	    		String content = EntityUtils.toString(entity).replace("ldWeather:source", "source");
	    		//Convert the response to String variable and replace "ldWeather:source" with "source" that contains no colon.
	    		
	    		pri = (PrimaryArea) unmarshaller.unmarshal(new StreamSource(new StringReader(content)));
	    		// Transform the new String variable into a File object for the unmarshaller and unmarshal the XML to convert it into a POJO.
	    		
	    		httpclient.close();
	    		//Close the http connection
	    		
	        }catch(ClientProtocolException e){
	        	e.printStackTrace();
	        }catch(IOException e){
	        	e.printStackTrace();
	        }catch (JAXBException e) {
				e.printStackTrace();
			} 
			
			return pri;
			//Return the object with data model PrimaryArea format
		}

}
