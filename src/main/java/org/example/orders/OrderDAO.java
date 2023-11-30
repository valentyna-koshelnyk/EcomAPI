package main.java.dev.ecom.orders;

import main.java.dev.ecom.products.Product;
import main.java.dev.ecom.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    public List<Order> getAllOrders() {
        List<Order> orderList = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM orders");
            while (rs.next()) {
                Order order = new Order (
                        rs.getInt("id"),
                        (Product) rs.getObject("productId"),
                        rs.getInt("quantity"),
                        rs.getString("customerDetails"));

                orderList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    public Order getOrder(int id) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM orders WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Order(
                        rs.getInt("id"),
                        (Product) rs.getObject("productId"),
                        rs.getInt("quantity"),
                        rs.getString("customerDetails"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addOrder(Order order) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO orders (productId, quantity, customerDetails) VALUES (?, ?, ?)");
            ps.setString(1, String.valueOf(order.getProductId()));
            ps.setDouble(2, order.getQuantity());
            ps.setString(3, order.getCustomerDetails());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateOrder(int id, Order order) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("UPDATE orders SET productId = ?, quantity = ?, customerDetails = ? WHERE id = ?");
            ps.setObject(1, order.getProductId());
            ps.setDouble(2, order.getQuantity());
            ps.setString(3, order.getCustomerDetails());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteOrder(int id) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM orders WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
