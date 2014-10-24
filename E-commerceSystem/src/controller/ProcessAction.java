package controller;

import java.util.*;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;
import dao.DaoFactory;
import model.*;

@SuppressWarnings("serial")
public class ProcessAction extends ActionSupport implements SessionAware {
	private String id;
	private String role;
	private String username;
	private String address;
	private String search;
	private static String result = " ";
	private List<Product> products;
	private List<CartData> cartData;
	private List<Order> order;
	private Order orders;
	private int orderid;
	private double totalPrice;
	@SuppressWarnings("rawtypes")
	private Map session;
	private int totalProducts;
	private String state;
	private double shippingfee;
	private dao.compute compute = new dao.compute();

	/*************************************************************
	 * get session
	 *************************************************************/
	@SuppressWarnings("rawtypes")
	public void setSession(Map value) {
		session = value;
	}

	/********************* Getters and Setters ****************************************/
	public void setRole(String role) {
		this.role = role;

	}

	public String getRole() {

		return role;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;

	}

	public int getOrderid() {

		return orderid;
	}

	public void setShippingfee(int shippingfee) {
		this.shippingfee = shippingfee;

	}

	public double getShippingfee() {

		return shippingfee;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setUsername(String username) {
		this.username = username;

	}

	public String getUsername() {
		return username;
	}

	public void setAddress(String address) {

		this.address = address;

	}

	public String getAddress() {
		return address;
	}

	@SuppressWarnings("static-access")
	public void setResult(String result) {
		this.result = result;
	}

	public String getResult() {
		return result;
	}

	public void setSearch(String search) {

		this.search = search;
	}

	public String getSearch() {

		return search;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<CartData> getCartData() {
		return cartData;
	}

	public void setCartData(List<CartData> cartData) {
		this.cartData = cartData;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getTotalProducts() {

		return totalProducts;
	}

	public void setTotalProducts(int totalProducts) {

		this.totalProducts = totalProducts;
	}

	/************************ action methods ********************************************/

	@SuppressWarnings("unchecked")
	public String loadquery() {
		session.put(Constants.QUERY, search);
		session.put("username", username);
		return SUCCESS;

	}

	public String show() {
		String search = (String) session.get(Constants.QUERY);
		if (search == null) {
			return "empty";
		} else {
			products = DaoFactory.getInstance().getProductDao()
					.ProductDaoMemImpl(search);

			return SUCCESS;
		}
	}

	// method supports dojo anchor tag
	@SuppressWarnings("unchecked")
	public String addDojo() {
		Object obj = session.get(Constants.CART);
		session.put("form", null);
		Cart cart = null;
		Product p = DaoFactory.getInstance().getProductDao().getProductById(id);

		if (obj == null) {
			cart = new Cart();
			cart.addItem(p);
			session.put(Constants.CART, cart);
		} else {
			cart = (Cart) obj;
			cart.addItem(p);
		}
		return SUCCESS;
	}

	// method to load cart
	public String loadCart() {
		Object obj = session.get(Constants.CART);
		Cart cart = null;
		if (obj == null) { // no cart info
			return Constants.NOCART;
		} else {
			cart = (Cart) obj;

			// The data will be access by cart_partial.jsp
			totalPrice = 0.0;
			totalProducts = 0;
			cartData = new ArrayList<CartData>();
			for (CartItem ci : cart.getItems()) {
				cartData.add(new CartData(ci.getProduct().getTitle(), ci
						.getQuantity(), ci.getProduct().getPrice()));
				totalPrice += ci.getQuantity() * ci.getProduct().getPrice();
				totalProducts += ci.getQuantity();

			}
			// System.out.print(totalProducts);
			return SUCCESS;
		}
	}

	@SuppressWarnings("unchecked")
	public String loadform() {
		session.put("form", "yes");
		return SUCCESS;
	}

	public String checkout() {

		String form = (String) session.get("form");
		if (form == null)
			return "noform";
		else
			return SUCCESS;

	}

	@SuppressWarnings("unchecked")
	public String removeDojo() {
		Object obj = session.get(Constants.CART);
		session.put("form", null);

		Cart cart = null;
		Product p = DaoFactory.getInstance().getProductDao().getProductById(id);
		cart = (Cart) obj;
		cart.removeItem(p);
		cart = (Cart) obj;
		if (cart.getItems().size() == 0) {

			session.put(Constants.CART, null);
		}

		return SUCCESS;

	}

	public void gettotal() {
		Object obj = session.get(Constants.CART);
		Cart cart = null;

		cart = (Cart) obj;

		// The data will be access by cart_partial.jsp
		totalPrice = 0.0;
		totalProducts = 0;
		cartData = new ArrayList<CartData>();
		for (CartItem ci : cart.getItems()) {
			cartData.add(new CartData(ci.getProduct().getTitle(), ci
					.getQuantity(), ci.getProduct().getPrice()));
			totalPrice += ci.getQuantity() * ci.getProduct().getPrice();
			totalProducts += ci.getQuantity();

		}

	}

	public String error() {

		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String showorder() {
		if (session.get("add") != null) {
			shippingfee = (Double) session.get("shippingfee");
			totalPrice = (Double) session.get("totalPrice");
			Object obj = session.get(Constants.CART);
			Cart cart = null;
			cart = (Cart) obj;
			cartData = new ArrayList<CartData>();
			for (CartItem ci : cart.getItems()) {
				cartData.add(new CartData(ci.getProduct().getTitle(), ci
						.getQuantity(), ci.getProduct().getPrice()));

			}

		}
		session.put("add", null);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String compute() throws Exception {
		gettotal();

		result = compute.computefee(address, totalProducts, totalPrice);

		if (result.equals("invalid"))
			return "invalidaddress";
		else {
			session.put("add", "yes");
			shippingfee = Math
					.round((Double.parseDouble(result) - totalPrice) * 100) * 1.0 / 100;
			totalPrice = Math.round(Double.parseDouble(result) * 100) * 1.0 / 100;
			dao.orderdata db = new dao.orderdata();
			try {

				db.add(username, totalProducts, shippingfee, totalPrice,
						address, "Processing");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Object obj = session.get(Constants.CART);
			Cart cart = null;
			cart = (Cart) obj;
			cartData = new ArrayList<CartData>();
			for (CartItem ci : cart.getItems()) {
				cartData.add(new CartData(ci.getProduct().getTitle(), ci
						.getQuantity(), ci.getProduct().getPrice()));
				totalPrice += ci.getQuantity() * ci.getProduct().getPrice();
				totalProducts += ci.getQuantity();
			}
			totalPrice = Math.round(Double.parseDouble(result) * 100) * 1.0 / 100;
			session.put("shippingfee", shippingfee);
			session.put("totalPrice", totalPrice);
			result = "";
			db.addorderdetails(cartData);
			return SUCCESS;

		}

	}

	@SuppressWarnings("unchecked")
	public String logout() {
		session.put("username", null);
		session.put(Constants.QUERY, null);
		session.put(Constants.CART, null);
		session.put("form", null);
		session.put("orderid", null);
		session.put("admin", null);
		session.put("address", null);
		session.put("add", null);
		session.put("shippingfee", null);
		session.put("totalPrice", null);
		result = "";
		return SUCCESS;

	}

	@SuppressWarnings("unchecked")
	public String discard() {
		session.put(Constants.CART, null);
		session.put("form", null);
		result = "";
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String loadorder() {
		session.put("username", username);
		return SUCCESS;
	}

	public String showcustomer() throws Exception {
		String username = (String) session.get("username");

		dao.orderdata db = new dao.orderdata();
		order = db.showcustomer(username);
		if (order.size() == 0) {

			return "empty";

		} else
			return SUCCESS;
	}

	public String shownewly() throws Exception {
		System.out.print(username);
		dao.orderdata db = new dao.orderdata();
		order = db.showcustomer(username);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String loaddetails() {
		session.put("orderid", orderid);

		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String loadadmin() {
		session.put("admin", username);
		return SUCCESS;
	}

	public String showorderdetails() throws Exception {
		int orderid = (Integer) session.get("orderid");
		dao.orderdata db = new dao.orderdata();
		order = db.showorderdetails(orderid);
		return SUCCESS;
	}

	public String showadmin() throws Exception {
		String admin = (String) session.get("admin");
		if (admin != null) {
			dao.orderdata db = new dao.orderdata();
			order = db.showadmin(username);
			return SUCCESS;
		} else
			return "empty";
	}

	public String updateorder() throws Exception {
		dao.orderdata db = new dao.orderdata();
		orders = db.getorderById(orderid);
		orders.setState(state);

		db.updateorder(orders);
		return SUCCESS;
	}

}
