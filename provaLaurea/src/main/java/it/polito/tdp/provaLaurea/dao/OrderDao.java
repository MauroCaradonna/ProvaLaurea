package it.polito.tdp.provaLaurea.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.polito.tdp.provaLaurea.model.Buyer;
import it.polito.tdp.provaLaurea.model.Invoice;
import it.polito.tdp.provaLaurea.model.Item;
import it.polito.tdp.provaLaurea.model.Order;
import it.polito.tdp.provaLaurea.model.Supplier;


public class OrderDao {

	public void getAllBuyers(Map<String, Buyer> buyerMap) {
		String sql = "SELECT * "
				   + "FROM buyer";
		//buyerMap = new HashMap<String, Buyer>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				String buyerId = res.getString("id");
				String buyerDescription = res.getString("value");
				
				Buyer n = new Buyer(buyerId, buyerDescription);
				buyerMap.put(buyerId, n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void getAllSuppliers(Map<String, Supplier> supplierMap) {
		String sql = "SELECT * "
				   + "FROM supplier";
		//supplierMap = new HashMap<String, Supplier>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				String supplierId = res.getString("id");
				String supplierDescription = res.getString("value");
				
				Supplier n = new Supplier(supplierId, supplierDescription);
				supplierMap.put(supplierId, n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void getAllItems(Map<String, Item> itemMap, Set<String> commoditySet) {
		String sql = "SELECT * "
				   + "FROM item";
		//itemMap = new HashMap<String, Item>();
		//commoditySet = new HashSet<String>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				String itemId = res.getString("id");
				String itemDescription = res.getString("value");
				String commodityDescription = res.getString("commodity");
				
				Item n = new Item(itemId, itemDescription, commodityDescription);
				itemMap.put(itemId, n);
				commoditySet.add(commodityDescription);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void getAllInvoices(Map<String, Invoice> invoiceMap) {
		String sql = "SELECT * "
				   + "FROM invoice";
		//invoiceMap = new HashMap<String, Invoice>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				String invoiceId = res.getString("id");
				LocalDate invoiceDate = res.getDate("date").toLocalDate();
				int quantity = res.getInt("quantity");
				double unitPurchasePrice = res.getDouble("unitPurchasePrice");
				double totalPurchasePrice = res.getDouble("totalPurchasePrice");
				
				Invoice n = new Invoice(invoiceId, invoiceDate, quantity, unitPurchasePrice, totalPurchasePrice);
				invoiceMap.put(invoiceId, n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void getAllOrders(Map<String, Order> orderMap, Map<String, Buyer> buyerMap, Map<String, Supplier> supplierMap, Map<String, Item> itemMap, Map<String, Invoice> invoiceMap) {
		String sql = "SELECT * "
				   + "FROM `order`";
		//orderMap = new HashMap<String, Order>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				String orderId = res.getString("id");
				String siteId = res.getString("site");
				Supplier supplier = supplierMap.get(res.getString("supplier"));
				Invoice invoice = invoiceMap.get(res.getString("invoice"));
				Item item = itemMap.get(res.getString("item"));
				Buyer buyer = buyerMap.get(res.getString("buyer"));
				
				Order n = new Order(orderId, siteId, supplier, invoice, item, buyer);
				orderMap.put(orderId, n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void addInvoice(Invoice invoice) {
		String sql = "INSERT INTO invoice "
				+ "VALUES (?,?,?,?,?)";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, invoice.getInvoiceId());
			st.setDate(2, Date.valueOf(invoice.getInvoiceDate()));
			st.setInt(3, invoice.getQuantity());
			st.setDouble(4, invoice.getUnitPurchasePrice());
			st.setDouble(5, invoice.getTotalPurchasePrice());
			st.executeQuery();
			
			st.close();
			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void addOrder(Order order) {
		String sql = "INSERT INTO `order` "
				   + "VALUES (?,?,?,?,?,?)";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, order.getOrderId());
			st.setString(2, order.getSiteId());
			st.setString(3, order.getSupplier().getSupplierId());
			st.setString(4, order.getInvoice().getInvoiceId());
			st.setString(5, order.getItem().getItemId());
			st.setString(6, order.getBuyer().getBuyerId());
			st.executeQuery();

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

}