package model;

public class Product {
	private String title;
	private String id;
	private double price;
	private String imageurl;
	private String tag;

	public Product() {
	}

	public Product(String id, String tag, String title, double price,
			String imageurl) {
		this.title = title;
		this.id = id;
		this.price = price;
		this.tag = tag;
		this.imageurl = imageurl;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String toString() {
		return "Product " + id + ": " + title + "@" + price;
	}

}
