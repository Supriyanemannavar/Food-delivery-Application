package com.tap.daoimpl;

import com.tap.dao.OrderItemDAO;
import com.tap.model.OrderItem;
import com.tap.utility.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDAOImpl implements OrderItemDAO {

    @Override
    public void addOrderItem(OrderItem item) {
        String query = "INSERT INTO OrderItems(orderid, menuid, quantity, subtotal) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, item.getOrderId());
            ps.setInt(2, item.getMenuId());
            ps.setInt(3, item.getQuantity());
            ps.setDouble(4, item.getSubTotal());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public OrderItem getOrderItemById(int orderItemId) {
        String query = "SELECT * FROM OrderItems WHERE orderitemid = ?";
        OrderItem  item = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, orderItemId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                item = extractOrderItem(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public List<OrderItem> getOrderItemsByOrderId(int orderId) {
        String query = "SELECT * FROM OrderItems WHERE orderid = ?";
        List<OrderItem> itemList = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                itemList.add(extractOrderItem(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemList;
    }

    @Override
    public List<OrderItem> getAllOrderItems() {
        String query = "SELECT * FROM OrderItems";
        List<OrderItem> itemList = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                itemList.add(extractOrderItem(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemList;
    }

    @Override
    public void updateOrderItem(OrderItem item) {
        String query = "UPDATE OrderItems SET orderid=?, menuid=?, quantity=?, subtotal=? WHERE orderitemid=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, item.getOrderId());
            ps.setInt(2, item.getMenuId());
            ps.setInt(3, item.getQuantity());
            ps.setDouble(4, item.getSubTotal());
            ps.setInt(5, item.getOrderItemId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrderItem(int orderItemId) {
        String query = "DELETE FROM OrderItems WHERE orderitemid = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, orderItemId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Mapping ResultSet → OrderItems object
    private OrderItem extractOrderItem(ResultSet rs) throws SQLException {
        return new OrderItem(
                rs.getInt("orderitemid"),
                rs.getInt("orderid"),
                rs.getInt("menuid"),
                rs.getInt("quantity"),
                rs.getDouble("subtotal")
        );
    }
}
