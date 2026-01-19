package com.tap.daoimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.OrdersDAO;
import com.tap.model.CartItem;
import com.tap.model.Orders;
import com.tap.utility.DBConnection;

public class OrdersDAOImpl implements OrdersDAO {

    // ========================= PLACE ORDER =========================
    @Override
    public int placeOrder(int userId, List<CartItem> items, double totalAmount, String paymentMode) {
        int orderId = -1;

        String insertOrder =
            "INSERT INTO orders(userId, restaurantId, orderDate, totalAmount, status, paymentMode) VALUES (?,?,?,?,?,?)";

        String insertItems =
            "INSERT INTO order_items(orderId, itemId, quantity, price) VALUES (?,?,?,?)";

        try (Connection con = DBConnection.getConnection()) {

            // -------- Insert order first --------
            PreparedStatement ps = con.prepareStatement(insertOrder, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setInt(1, userId);
            ps.setInt(2, items.get(0).getRestaurantid()); // restaurant id fetched from cart
            ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            ps.setDouble(4, totalAmount);
            ps.setString(5, "Placed");
            ps.setString(6, paymentMode);

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) orderId = rs.getInt(1);

            // -------- Insert order items --------
            PreparedStatement ps2 = con.prepareStatement(insertItems);

            for (CartItem item : items) {
                ps2.setInt(1, orderId);
                ps2.setInt(2, item.getItemId());
                ps2.setInt(3, item.getQuantity());
                ps2.setDouble(4, item.getPrice());
                ps2.executeUpdate();
            }

            System.out.println("Order placed successfully! Order ID: " + orderId);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderId;
    }


    // ========================= ADD ORDER (If using Order Model Insert) =========================
    @Override
    public int addOrder(Orders order) {
        int orderId = -1;
        String sql = "INSERT INTO orders (userId, restaurantId, orderDate, totalAmount, status, paymentMode) VALUES (?,?,?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, order.getUserId());
            ps.setInt(2, order.getRestaurantId());
            ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            ps.setDouble(4, order.getTotalAmount());
            ps.setString(5, order.getStatus());
            ps.setString(6, order.getPaymentMode());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) orderId = rs.getInt(1);

        } catch (Exception e) { e.printStackTrace(); }

        return orderId;
    }


    // ========================= GET ORDER BY ID =========================
    @Override
    public Orders getOrderById(int orderId) {
        Orders order = null;
        String sql = "SELECT * FROM orders WHERE orderId=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                order = new Orders();
                order.setOrderId(rs.getInt("orderId"));
                order.setUserId(rs.getInt("userId"));
                order.setRestaurantId(rs.getInt("restaurantId"));
                order.setOrderDate(rs.getTimestamp("orderDate"));
                order.setTotalAmount(rs.getDouble("totalAmount"));
                order.setStatus(rs.getString("status"));
                order.setPaymentMode(rs.getString("paymentMode"));
            }
        } catch (Exception e) { e.printStackTrace(); }

        return order;
    }


    // ========================= UPDATE ORDER =========================
    @Override
    public void updateOrder(Orders order) {
        String sql = "UPDATE orders SET status=?, paymentMode=? WHERE orderId=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, order.getStatus());
            ps.setString(2, order.getPaymentMode());
            ps.setInt(3, order.getOrderId());

            ps.executeUpdate();

        } catch (Exception e) { e.printStackTrace(); }
    }


    // ========================= DELETE ORDER =========================
    @Override
    public void deleteOrder(int orderId) {
        String sql = "DELETE FROM orders WHERE orderId=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            ps.executeUpdate();

        } catch (Exception e) { e.printStackTrace(); }
    }


    // ========================= GET ALL ORDERS OF USER =========================
    @Override
    public List<Orders> getAllOrders(int userId) {
        List<Orders> list = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE userId=? ORDER BY orderId DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Orders order = new Orders();
                order.setOrderId(rs.getInt("orderId"));
                order.setUserId(rs.getInt("userId"));
                order.setRestaurantId(rs.getInt("restaurantId"));
                order.setOrderDate(rs.getTimestamp("orderDate"));
                order.setTotalAmount(rs.getDouble("totalAmount"));
                order.setStatus(rs.getString("status"));
                order.setPaymentMode(rs.getString("paymentMode"));
                list.add(order);
            }

        } catch (Exception e) { e.printStackTrace(); }

        return list;
    }
}
