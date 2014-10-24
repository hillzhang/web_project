package model;

public class Order {
	private String username;
	private int totalnum;
	private double totalprice;
	private int orderid;
	private String address;
	private double shippingfee;
	private String state;
	private String title;

	public Order(int orderid, String username, int totalnum,
			double shippingfee, double totalprice, String address, String state) {

		this.username = username;
		this.totalnum = totalnum;
		this.totalprice = totalprice;
		this.orderid = orderid;
		this.state = state;
		this.address = address;
		this.shippingfee = shippingfee;

	}

	public Order(String title, int totalnum, double totalprice) {
		this.title = title;
		this.totalnum = totalnum;
		this.totalprice = totalprice;

	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public int getOrderid() {

		return orderid;
	}

	public void setUsername(String username) {
		this.username = username;

	}

	public String getUsername() {
		return username;
	}

	public void setTotalnum(int totalnum) {
		this.totalnum = totalnum;
	}

	public int getTotalnum() {
		return totalnum;
	}

	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}

	public double getTotalprice() {

		return totalprice;
	}

	public void setShippingfee(double shippingfee) {
		this.shippingfee = shippingfee;
	}

	public double getShippingfee() {

		return shippingfee;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

}
