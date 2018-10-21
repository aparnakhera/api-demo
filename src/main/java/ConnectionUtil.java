import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.apache.log4j.Logger;

/*This class is used to connect or disconnect to the API url. */
public class ConnectionUtil {

	private HttpURLConnection conn;

	final static Logger logger = Logger.getLogger(ConnectionUtil.class);

	// Method to connect to the API
	public String connect(URL url) throws Exception {
		String response = new String();
		try {
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			if (conn.getResponseCode() != 200) {
				logger.error("Invalid response code. API Connection failed.");
				throw new RuntimeException("HTTP error code: " + conn.getResponseCode());
			}
			logger.info("API Connection success.");

			logger.info("Scanning response.");
			Scanner scan = new Scanner(url.openStream());
			while (scan.hasNext()) {
				response += scan.nextLine();
			}
			scan.close();
		} catch (MalformedURLException e) {
			logger.error("MalformedURLException has occured. API Connection failed.");

		} catch (IOException e) {
			logger.error("IOException has occured. API Connection failed.");
		}
		return response;
	}

	// Method to disconnect from the API
	public void disconnect() {
		if (conn != null) {
			conn.disconnect();
		}
	}
}
