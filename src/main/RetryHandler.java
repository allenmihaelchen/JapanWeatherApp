package main;

import java.io.InterruptedIOException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLException;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.protocol.HttpClientContext;

public class RetryHandler {
	
	public HttpRequestRetryHandler retryHandler(){
        return (exception, count, context) -> {

            System.out.println("trying to connect again: " + count);

            if (count >= 5) {
                // Set the maximum retry times
                return false;
            }
            if (exception instanceof InterruptedIOException) {
                // Retry for Timeout
                return false;
            }
            if (exception instanceof UnknownHostException) {
                // Retry for Unknown host
                return false;
            }
            if (exception instanceof SSLException) {
                // Retry for SSL handshake exception
                return false;
            }
            
            HttpClientContext httpClientContext = HttpClientContext.adapt(context);
            HttpRequest request = httpClientContext.getRequest();
            boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
            
            if (idempotent) {
                // Retry if the request is considered idempotent
                return true;
            }
            return false;
        };
	}

}
