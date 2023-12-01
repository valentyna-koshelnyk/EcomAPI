package org.example.orders;


import org.example.products.Product;
import org.example.utils.DBConnection;

import org.example.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    private static final String SELECT_ALL_ORDERS = "SELECT * from orders";
    private static final String SELECT_ORDER = "SELECT * from orders WHERE id = ?";
    private static final String UPDATE_ORDER = "UPDATE orders " +
            "SET productId = ?, quantity = ?, " +
            "customerDetails=? WHERE id = ?";

    private static final String ADD_ORDER = "INSERT into orders " +
            "VALUES 0, ?, ?, ?";
    private static final String DELETE_ORDER = "DELETE from orders " +
            "WHERE id = ?";
    private static final String FILTER_BY_DATE = "SELECT * FROM orders" +
            "WHERE OrderDate BETWEEN  ? AND ?;";

    public List<Order> getAllOrders() {
        List<Order> orderList = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_ALL_ORDERS);
            while (rs.next()) {
                Order order = new Order (
                        rs.getInt("id"),
                        rs.getInt("productId"),
                        rs.getInt("quantity"),
                        rs.getString("customerDetails"),
                        rs.getDate("date"));

                orderList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    public Order getOrder(int id) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(SELECT_ORDER);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Order(
                        rs.getInt("id"),
                        rs.getInt("productId"),
                        rs.getInt("quantity"),
                        rs.getString("customerDetails"),
                        rs.getDate("date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Order> getOrdersByDateRange(Date startDate, Date endDate) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(FILTER_BY_DATE);
            ps.setDate(1, startDate);
            ps.setDate(2, endDate);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    List<Order> orders = (List<Order>) new Order(
                            rs.getInt("id"),
                            rs.getInt("productId"),
                            rs.getInt("quantity"),
                            rs.getString("customerDetails"),
                            rs.getDate("date"));
                    return orders;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void addOrder(Order order) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(ADD_ORDER);
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
            PreparedStatement ps = connection.prepareStatement(UPDATE_ORDER);
            ps.setObject(1, order.getProductId());
            ps.setDouble(2, order.getQuantity());
            ps.setString(3, order.getCustomerDetails());
            ps.setInt(4, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteOrder(int id) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(DELETE_ORDER);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}