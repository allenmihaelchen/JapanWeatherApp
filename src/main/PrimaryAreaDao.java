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


public class PrimaryAreaDao{
	    

	    
		public PrimaryArea fetchArea(String areaURL){
			
			//Make the RetryHandler ready
			RetryHandler retryHandler = new RetryHandler();
			
			//Implement HttpRequestRetryHandler in HttpClients 
			CloseableHttpClient httpclient = HttpClients.custom().setRetryHandler(retryHandler.retryHandler()).build();
			
			//Initiate the PrimaryArea object to be returned 
			PrimaryArea pri = null;
			
			//Establish a http connection with Apache HttpClient 
	        try (CloseableHttpResponse response = httpclient.execute(new HttpGet(areaURL));){
	            
	        	//Get the http status codes
				int rc = response.getStatusLine().getStatusCode();
				
				//Get the response entity
				HttpEntity entity = response.getEntity();
				
				//Execute the data processing if the http connection is ok and the response entity is not null
				if(rc==200 && entity!=null){
					
					JAXBContext jaxbContext = JAXBContext.newInstance(PrimaryArea.class);
					
					//Utilize JAXB to parse the XML
					Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
					
					//Convert the response to String variable and replace "ldWeather:source" with "source" that contains no colon
					String content = EntityUtils.toString(entity).replace("ldWeather:source", "source");
					
					// Transform the new String variable into a File object for the unmarshaller and unmarshal the XML to convert it into a POJO
					pri = (PrimaryArea) unmarshaller.unmarshal(new StreamSource(new StringReader(content)));
					
					EntityUtils.consume(entity);
					
				}else if(rc!=200){
					System.out.println("Http connection has sth wrong and the status code is "+rc);
				}else if(response.getEntity()==null){
					System.out.println("Got nothing back from the http connection!");
				}
				
				//Closs the http connection    
				try {
				      response.close();
					} catch(Exception e) {
			          e.printStackTrace();
			        }	
	    		
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
