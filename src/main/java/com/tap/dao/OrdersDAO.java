package com.tap.dao;

import java.util.List;
import com.tap.model.CartItem;
import com.tap.model.Orders;

public interface OrdersDAO {
    public int placeOrder(int userId, List<CartItem> items, double totalAmount, String paymentMode);

    int addOrder(Orders order);
    Orders getOrderById(int orderId);
    void updateOrder(Orders order);
    void deleteOrder(int orderId);
    List<Orders> getAllOrders(int userId);
}
