package dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class compute {
	private String result;

	public String computefee(String address, int total, double itemfee) {

		URL url = null;
		try {
			url = new URL("http://localhost:8080/rest/rest/compute?totalnum="
					+ total + "&address=" + address + "&itemfee=" + itemfee);
			HttpURLConnection connet;
			connet = (HttpURLConnection) url.openConnection();
			if (connet.getResponseCode() != 200) {

				throw new IOException(connet.getResponseMessage());

			}
			BufferedReader brd = new BufferedReader(new InputStreamReader(
					connet.getInputStream()));
			result = brd.readLine();
			connet.disconnect();

			return result;

		} catch (IOException e) {
			e.printStackTrace();

		}
		return result;

	}

}
