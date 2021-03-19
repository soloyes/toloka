import account.ConnectionParameters;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class Connection {
	public static JsonElement getDataBy(String endpoint) {
		try {
			URL url = new URL(ConnectionParameters.URL + endpoint);
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			connection.setRequestProperty("Authorization", ConnectionParameters.TOKEN_TYPE + " " + ConnectionParameters.TOKEN);
			connection.setDoOutput(true);
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");

			if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ connection.getResponseCode());
			}

			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((connection.getInputStream())));


			JsonElement jsonElement = JsonParser.parseReader(bufferedReader);

			bufferedReader.close();
			connection.disconnect();

			return jsonElement;

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return JsonParser.parseString("{\"error\":\"0\"}");
	}
}