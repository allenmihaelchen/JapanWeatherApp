import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class main {

	public final static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String baseURL = "http://weather.livedoor.com/forecast/webservice/json/v1?city=016010";
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet(baseURL);

            System.out.println("Executing request " + httpget.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httpget);
            BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
            
            System.out.println("----------------------------------------");
            System.out.println(response.getStatusLine());
            
            System.out.println("----------------------------------------");
            String output;
    		System.out.println("Output from Server .... \n");
    		while ((output = br.readLine()) != null) {
    			System.out.println(output);
    		}
            
        }catch(ClientProtocolException e){
        	e.printStackTrace();
        }catch(IOException e){
        	e.printStackTrace();
        } 
        finally {
            httpclient.close();
        }
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
