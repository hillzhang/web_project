package dao;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import controller.Constants;
import model.Product;

public class ProductDaoMemImpl implements ProductDao {

	private List<Product> products;

	public List<Product> ProductDaoMemImpl(String pandaName) {

		try {
			products = new ArrayList<Product>();
			String callUrlStr = Constants.REST_ENDPOINT + "?method="
					+ Constants.METHOD + "&tags="
					+ URLEncoder.encode(pandaName, Constants.ENC) + "&extras="
					+ Constants.DEFAULT_TAGS + "&per_page="
					+ Constants.DEFAULT_NUMBER + "&api_key="
					+ Constants.API_KEY;

			URL callUrl = new URL(callUrlStr);
			HttpURLConnection urlConnection = (HttpURLConnection) callUrl
					.openConnection();
			InputStream urlStream = urlConnection.getInputStream();

			DocumentBuilder db = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document response = db.parse(urlStream);

			// print out all titles
			// System.out.println("The titles returned are: ");
			NodeList nl = response.getElementsByTagName("photo");
			for (int i = 0; i < nl.getLength(); i++) {
				String tag = nl.item(i).getAttributes().getNamedItem("tags")
						.getTextContent();
				String id = nl.item(i).getAttributes().getNamedItem("id")
						.getTextContent();
				String title = nl.item(i).getAttributes().getNamedItem("title")
						.getTextContent();
				String farm_id = nl.item(i).getAttributes()
						.getNamedItem("farm").getTextContent();
				String server_id = nl.item(i).getAttributes()
						.getNamedItem("server").getTextContent();
				String secret = nl.item(i).getAttributes()
						.getNamedItem("secret").getTextContent();
				String url = "http://farm" + farm_id + ".staticflickr.com/"
						+ server_id + "/" + id + "_" + secret + "_s.jpg";
				String[] tags = tag.split(" ");

				int tagnum = tags.length;
				double price = 3 + tagnum;

				products.add(new Product(id, tag, title, price, url));
				//
			}
			urlConnection.disconnect();
			return products;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	public Product getProductById(String prodId) {
		// TODO Auto-generated method stub

		for (Product prod : products) {
			String pro = prod.getId();
			if (pro.equals(prodId))
				return prod;

		}
		return null;
	}

}
