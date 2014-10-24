package commons;

import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/compute")
public class compute {
	private HashMap<String, Double> address;
	private double pershifee = 0.99;
	private double price;

	private String finalprice;

	public compute() {
		address = new HashMap<String, Double>();
		address.put("sydney", 7.99);
		address.put("melbourne", 14.99);
		address.put("canbrra", 13.99);
		address.put("Brisbane", 14.99);
	}

	public void startup() {
		System.out.println("Starting server.........");
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getResult(@QueryParam("totalnum") int totalnum,
			@QueryParam("address") String address,
			@QueryParam("itemfee") double itemfee) {
		System.out.println("Current state: receive order info from customer!!");
		System.out.println("order info:");
		System.out.println("Quantity:" + totalnum);
		System.out.println("Itemprice:$" + itemfee);
		System.out.println("Address:" + address);

		return result(totalnum, address, itemfee);
	}

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String postResult(@QueryParam("totalnum") int totalnum,
			@QueryParam("address") String address,
			@QueryParam("itemfee") double itemfee) {
		System.out.println("Current state: receive order info from customer!!");
		System.out.println("order info:");
		System.out.println("Quantity:" + totalnum);
		System.out.println("Itemprice:$" + itemfee);
		System.out.println("Adress:" + address);
		return result(totalnum, address, itemfee);
	}

	private String result(int totalnum, String address, double itemfee) {

		int num = 0;
		for (String add : this.address.keySet()) {
			if (address.equals(add)) {
				price = this.address.get(add);
				num++;
			}

		}
		System.out.println("Current state: Computing fee.......!!");
		if (num > 0) {

			finalprice = Double.toString(Math.round((totalnum * pershifee
					+ price + itemfee) * 100) * 1.0 / 100);
			String shippingfee = Double.toString(Math.round((totalnum
					* pershifee + price) * 100) * 1.0 / 100);

			System.out.println("Shipping fee:$" + shippingfee);
			System.out.println("Final fee:$" + finalprice);
		} else {

			finalprice = "invalid";
			System.out.println("Address is invalid, please try again!!!");

		}

		return finalprice;

	}

}
