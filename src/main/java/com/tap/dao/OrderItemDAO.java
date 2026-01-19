
package com.tap.dao;

import com.tap.model.OrderItem;
import java.util.List;

public interface OrderItemDAO {
    void addOrderItem(OrderItem item);
    OrderItem getOrderItemById(int orderItemId);
    List<OrderItem> getOrderItemsByOrderId(int orderId);
    List<OrderItem> getAllOrderItems();
    void updateOrderItem(OrderItem item);
    void deleteOrderItem(int orderItemId);
}

