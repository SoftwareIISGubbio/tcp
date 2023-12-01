import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Carica {
	
	public static void main(String args[]) throws Exception {
		
	    for(int i=0;i<36000;i++){
	    	int t = 20+(int)(Math.random()*40);
		    int p = 40+(int)(Math.random()*60);
		    double c = (Math.random()*5);
		    int r = 100+(int)(Math.random()*100);
		    String URL = "http://localhost:8008/ins?t="+t+"+&p="+p+"&c="+c+"&r="+r+"&f=simulatore";
		    System.out.println(URL);
		    sendGET(URL);
		    Thread.sleep(1000);
	    }
	}
	
	private static void sendGET(String get_url) throws IOException {
		URL obj = new URL(get_url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		int responseCode = con.getResponseCode();
	}
}
