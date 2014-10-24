package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import model.CartData;
import model.Order;

public class orderdata {
	private DataSource ds;
	private String getProductByIdSQL = "SELECT * from orderdata where orderid = ?",
			getAllProductsSQL = "SELECT * from orderdata",
			insertProductSQL = "INSERT into orderdata(username,totalnum,shippingfee,totalprice,address,state) value (?,?,?,?,?,?)",
			updateProductSQL = "UPDATE orderdata set username=?, totalnum=?, totalprice=?, state=? where orderid = ?",
			selectlastid = "SELECT max(orderid) FROM orderdata",
			insertdetails = "INSERT into orderdetails(title,quantity,price,orderid) value (?,?,?,?)";

	public orderdata() throws Exception {

		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			// Look up our data source
			ds = (DataSource) envCtx.lookup("jdbc/shoppingcart");

		} catch (NamingException e) {
			throw new Exception("cannot find database");
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public void add(String username, int totalnum, double shippingfee,
			double totalPrice, String address, String state) {
		try {

			Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(insertProductSQL);
			ps.setString(1, username);
			ps.setInt(2, totalnum);
			ps.setDouble(3, Math.round(shippingfee * 100) * 1.0 / 100);
			ps.setDouble(4, Math.round(totalPrice * 100) * 1.0 / 100);
			ps.setString(5, address);
			ps.setString(6, state);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.getMessage();
		}

	}

	public List<Order> showcustomer(String username) {
		String getAllProductsSQL = "SELECT * from orderdata where username="
				+ "\"" + username + "\"";
		List<Order> orders = new ArrayList<Order>();
		try {
			Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(getAllProductsSQL);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				orders.add(new Order(rs.getInt("orderid"), rs
						.getString("username"), rs.getInt("totalnum"), rs
						.getDouble("shippingfee"), rs.getDouble("totalprice"),
						rs.getString("address"), rs.getString("state")));

			}
			rs.close();
			ps.close();
			conn.close();
			return orders;
		} catch (Exception e) {
			e.getMessage();
			return null;
		}

	}

	public List<Order> showadmin(String username) {

		List<Order> orders = new ArrayList<Order>();
		try {
			Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(getAllProductsSQL);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				orders.add(new Order(rs.getInt("orderid"), rs
						.getString("username"), rs.getInt("totalnum"), rs
						.getDouble("shippingfee"), rs.getDouble("totalprice"),
						rs.getString("address"), rs.getString("state")));
			}
			rs.close();
			ps.close();
			conn.close();
			return orders;
		} catch (Exception e) {
			e.getMessage();
			return null;
		}

	}

	public Order getorderById(int orderid) {
		try {
			Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(getProductByIdSQL);
			ps.setInt(1, orderid);
			ResultSet rs = ps.executeQuery();
			Order p = null;
			if (rs.next()) {
				p = (new Order(rs.getInt("orderid"), rs.getString("username"),
						rs.getInt("totalnum"), rs.getDouble("shippingfee"),
						rs.getDouble("totalprice"), rs.getString("address"),
						rs.getString("state")));
			}
			rs.close();
			ps.close();
			conn.close();
			return p;
		} catch (SQLException e) {
			return null;
		}
	}

	public void updateorder(Order p) {

		try {
			Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(updateProductSQL);
			ps.setString(1, p.getUsername());
			ps.setInt(2, p.getTotalnum());
			ps.setDouble(3, p.getTotalprice());
			ps.setString(4, p.getState());
			ps.setInt(5, p.getOrderid());
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.getMessage();
		}

	}

	public void addorderdetails(List<CartData> cartData) {
		// TODO Auto-generated method stub
		Connection conn;
		try {
			conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(selectlastid);
			ResultSet rs = ps.executeQuery();
			int orderid = 0;
			if (rs.next()) {
				orderid = rs.getInt("max(orderid)");
			}
			PreparedStatement ps1 = conn.prepareStatement(insertdetails);

			List<CartData> orderdetail = cartData;
			for (int i = 0; i < orderdetail.size(); i++) {
				CartData cd = orderdetail.get(i);
				String title = cd.getTitle();
				int quantity = cd.getQuantity();
				double price = cd.getPrice() * quantity;
				ps1.setString(1, title);
				ps1.setInt(2, quantity);
				ps1.setDouble(3, Math.round(price * 100) * 1.0 / 100);
				ps1.setInt(4, orderid);
				ps1.executeUpdate();
			}
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<Order> showorderdetails(int orderid) {
		List<Order> orders = new ArrayList<Order>();
		String showorderdetails = "SELECT * from orderdetails where orderid="
				+ orderid;
		try {
			Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(showorderdetails);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				orders.add(new Order(rs.getString("title"), rs
						.getInt("quantity"), rs.getDouble("price")));

			}
			rs.close();
			ps.close();
			conn.close();
			return orders;
		} catch (Exception e) {
			e.getMessage();
			return null;
		}

	}

}
