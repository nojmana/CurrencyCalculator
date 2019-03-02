package pl.sygnity.converter.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NbpApiHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(Converter.class);
	
	private String baseURL = "http://api.nbp.pl/api/exchangerates/rates/A/";
	private String fullURL;

	public Double getConverterValue(String currency, LocalDate date) {			
		fullURL = baseURL + currency + "/" + formatDate(date);
		logger.info("Connecting to: " + fullURL);

		HttpURLConnection request = connectToAPI();
		InputStream inputStream;
		try {
			inputStream = request.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
			throw new MyException(404, "Cannot access API at given URL", e);
		}

		BufferedReader reader;
		String json = "";
		reader = new BufferedReader(new InputStreamReader(inputStream));
		json = new String();
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				json += line + "\n";
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new MyException(404, "Error while parsing JSON", e);
		}

		JSONObject obj = new JSONObject(json);
		JSONArray array = obj.getJSONArray("rates");
			
		for (int i = 0; i < array.length();) {
			JSONObject item = array.getJSONObject(i);
			Double converter = (Double) item.get("mid");
			logger.info(currency + " converter: " + converter);
			return Double.valueOf(converter);
		}
		return Double.valueOf(0);
	}
	
	
	public HttpURLConnection connectToAPI() {
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.56.3.1", 8080));
		URL url;
		HttpURLConnection request = null;
		try {
			url = new URL(this.fullURL);
			request = (HttpURLConnection) url.openConnection(proxy);
//			request = (HttpURLConnection) url.openConnection();
			request.setRequestProperty("Accept", "application/json");
			request.connect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new MyException(404, "Malformed URL", e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new MyException(404, "Cannot access API at given URL", e);
		}
		return request;
	}
	
	public String formatDate(LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedString = date.format(formatter);
		return formattedString;
	}
	
}
