package com.tap.Servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Map;

import com.tap.dao.OrderItemDAO;
import com.tap.dao.OrdersDAO;
import com.tap.daoimpl.OrderItemDAOImpl;
import com.tap.daoimpl.OrdersDAOImpl;
import com.tap.model.Cart;
import com.tap.model.CartItem;
import com.tap.model.OrderItem;
import com.tap.model.Orders;
import com.tap.model.User;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;





@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    private OrdersDAO ordersDAO;
    private OrderItemDAO orderItemDAO;

    @Override
    public void init() {
        ordersDAO = new OrdersDAOImpl();
        orderItemDAO = new OrderItemDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("loggedInUser");

        if (cart != null && user != null && !cart.getItems().isEmpty()) {

            String paymentMethod = request.getParameter("paymentMethod");
            String address = request.getParameter("address");

            // Create Order
            Orders order = new Orders();
            order.setUserId(user.getUserId());
            order.setRestaurantId((int) session.getAttribute("restaurantId"));
            order.setOrderDate(new java.sql.Timestamp(System.currentTimeMillis()));
            order.setPaymentMode(paymentMethod);
            order.setAddress(address);
            order.setStatus("Pending");

            // Calculate total
            double totalAmount = 0;
            for (CartItem item : cart.getItems()) {
                totalAmount += item.getPrice() * item.getQuantity();
            }
            order.setTotalAmount(totalAmount);

            // Save order
            int orderId = ordersDAO.addOrder(order); // return generated orderId
            order.setOrderId(orderId);

            // Save each cart item
            for (CartItem cartItem : cart.getItems()) {

                OrderItem oi = new OrderItem();
                oi.setOrderId(orderId);
                oi.setMenuId(cartItem.getItemId());  // <-- correct (itemId = menuId in cart)
                oi.setQuantity(cartItem.getQuantity());
                oi.setSubTotal(cartItem.getTotalPrice());

                orderItemDAO.addOrderItem(oi);
            }

            // Clear cart
            session.removeAttribute("cart");

            // Save order info in session for JSP
            session.setAttribute("order", order);
            session.setAttribute("orderItems", cart.getItems());

            response.sendRedirect("order_confirmation.jsp");

        } else {
            response.sendRedirect("Thankyou.jsp");
        }
    }
}