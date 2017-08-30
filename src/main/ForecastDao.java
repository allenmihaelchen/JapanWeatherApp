package main;

import java.io.IOException;
import java.net.MalformedURLException;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import weatharForecastDto.WeatherEntity;
import main.RetryHandler;

public class ForecastDao {
	
	public WeatherEntity fetchForecast(String cityURL){
		
		//Make the RetryHandler ready
		RetryHandler retryHandler = new RetryHandler();
		
		//Implement HttpRequestRetryHandler in HttpClients 
		CloseableHttpClient httpclient = HttpClients.custom().setRetryHandler(retryHandler.retryHandler()).build();
		
		//Initiate the WeatherEntity object to be returned
		WeatherEntity obj = null;

		
		try(CloseableHttpResponse response = httpclient.execute(new HttpGet(cityURL));){
			
			//Get the http status codes
			int rc = response.getStatusLine().getStatusCode();
			
			//Get the response entity
			HttpEntity entity = response.getEntity();
			
			//Execute the data processing if the http connection is ok and the response entity is not null
			if(rc==200 && entity!=null){
				
				ObjectMapper mapper = new ObjectMapper();
				obj = mapper.readValue(entity.getContent(), WeatherEntity.class);
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
        }catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(JsonMappingException e){
			e.printStackTrace();
		}catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return obj;
	}

}


