import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.net.URIBuilder;

public class HttpDelegate {
	private static CloseableHttpClient httpClient;
	private String host;
	
	private static HttpDelegate istance=null;
	public static synchronized HttpDelegate open(String hostname){
		if(istance==null){
			istance=new HttpDelegate();
			httpClient=HttpClients.createDefault();
			istance.host=hostname;
		}
		return istance;
		
	}
	
	
	
    public String receive() throws URISyntaxException, IOException, ParseException{
		URI uri=new URIBuilder().setScheme("http")
				.setHost(host)
				.setPort(8080)
				.setPath("ChatController/ChatController")
				.build();
		HttpGet request=new HttpGet(uri);
		CloseableHttpResponse response=httpClient.execute(request);
		String responseBody=EntityUtils.toString(response.getEntity());
		response.close();
		return responseBody;
	}
	
	public void send(String message) throws URISyntaxException, IOException{
			URI uri= new URIBuilder()
					.setScheme("http")
					.setHost(host)
					.setPort(8080)
					.setPath("/ChatController/ChatController")
					.build();
			HttpPost request=new HttpPost(uri);
			request.setEntity(new StringEntity(message));
			CloseableHttpResponse response=httpClient.execute(request);
			response.close();
	}
	
	public void close() throws IOException{
		if(istance!=null){
			httpClient.close();
			istance=null;
		}
	}
	
	

	}



