package pl.sygnity.dimon.converter.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonParser {
	
	private static final Logger logger = LoggerFactory.getLogger(Converter.class);
	
	private String baseURL = "http://api.nbp.pl/api/exchangerates/rates/";
	private String fullURL;
	private HttpURLConnection request;

	public Double getConverterValue(String currency) {
		for (CurrencyTable table: CurrencyTable.values()) {
			fullURL = baseURL + table + "/" + currency;
			logger.info("Connecting to: " + fullURL);

			connect();

	        BufferedReader reader;
	        String json = "";
			try {
				reader = new BufferedReader(new InputStreamReader(this.request.getInputStream()));
		        json = new String();
		        String line;
		        while ((line = reader.readLine()) != null) {
		        	json += line + "\n";
		        }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			JSONObject obj = new JSONObject(json);
			JSONArray array = obj.getJSONArray("rates");
			
			for (int i = 0; i < array.length(); i++) {
				JSONObject item = array.getJSONObject(i);
				Double converter = (Double) item.get("mid");
				logger.info("Converter to currency " + currency + ": " + converter);
				return Double.valueOf(converter);
			}
			
		}
		return Double.valueOf(0);
	}
	
	
	public void connect() {
//		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.56.3.1", 8080));
		URL url;
		try {
			url = new URL(fullURL);
//			this.request = (HttpURLConnection) url.openConnection(proxy);
			this.request = (HttpURLConnection) url.openConnection();
			this.request.setRequestProperty("Accept", "application/json");
			this.request.connect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
