<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.tap.model.Cart" %>
<%@ page import="com.tap.model.CartItem" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Your Cart</title>

<style>
body {
    font-family: Poppins, Arial, sans-serif;
    background: #f2f4f7;
    margin: 0;
}

/* Navbar */
.navbar {
    background-color: #ff4d4d;
    padding: 18px;
    color: white;
    font-size: 26px;
    text-align: center;
    font-weight: bold;
    letter-spacing: 1px;
    box-shadow: 0 3px 10px rgba(0,0,0,0.2);
}

/* Container */
.cart-container {
    width: 85%;
    max-width: 900px;
    margin: 40px auto;
}

/* Cart Item Card */
.cart-card {
    background: white;
    padding: 20px;
    border-radius: 14px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    border: 1px solid #f7d9d9;
    box-shadow: 0 4px 12px rgba(0,0,0,0.1);
    margin-bottom: 18px;
    transition: 0.2s ease-in-out;
}
.cart-card:hover {
    transform: scale(1.02);
}

/* Item Info */
.item-info {
    display: flex;
    flex-direction: column;
}
.item-name {
    font-size: 22px;
    font-weight: 600;
    color: #222;
}
.item-price {
    font-size: 17px;
    color: #555;
    margin-top: 5px;
}

/* Buttons */
.item-actions {
    display: flex;
    align-items: center;
    gap: 12px;
}
.qty-btn {
    padding: 8px 16px;
    background: #ffe2e2;
    color: #ff4d4d;
    border-radius: 10px;
    border: none;
    font-size: 20px;
    cursor: pointer;
    transition: 0.2s;
    font-weight: bold;
}
.qty-btn:hover {
    background: #ffcccc;
}
.qty-btn:disabled {
    background: #ddd;
    color: #999;
    cursor: not-allowed;
}
.remove-btn {
    border: none;
    padding: 10px 16px;
    background: #ff3333;
    color: white;
    font-size: 15px;
    border-radius: 10px;
    cursor: pointer;
    font-weight: bold;
    transition: 0.2s;
}
.remove-btn:hover {
    background: #e60000;
}

/* Add More Items */
.add-more-btn {
    padding: 14px 28px;
    font-size: 18px;
    background: #007bff;
    color: white;
    border: none;
    border-radius: 12px;
    cursor: pointer;
    margin-top: 30px;
    transition: 0.2s;
}
.add-more-btn:hover {
    background: #005fcc;
}

/* Total */
.cart-total {
    width: 85%;
    max-width: 900px;
    margin: 20px auto;
    font-size: 26px;
    text-align: right;
    font-weight: bold;
    color: #222;
}

/* Checkout Button */
.checkout-btn {
    display: block;
    width: 280px;
    margin: 20px auto;
    padding: 15px;
    text-align: center;
    background: #ff4d4d;
    color: white;
    border: none;
    border-radius: 14px;
    font-size: 20px;
    cursor: pointer;
    font-weight: bold;
    transition: 0.2s;
    text-decoration: none;
}
.checkout-btn:hover {
    background: #e60000;
}

/* Empty Cart */
.empty-cart {
    text-align: center;
    font-size: 22px;
    color: #777;
    margin-top: 70px;
}

</style>
</head>
<body>

<div class="navbar">🛒 Your Cart Items</div>

<%
Cart cart = (Cart) session.getAttribute("cart");
Integer restaurantId = (Integer) session.getAttribute("restaurantId");

List<CartItem> cartItems = null;
double total = 0;
if (cart != null) cartItems = cart.getItems();
%>

<div class="cart-container">

<% if (cartItems != null && !cartItems.isEmpty()) { 
    for (CartItem c : cartItems) {
        double itemTotal = c.getPrice() * c.getQuantity();
        total += itemTotal;
%>

<div class="cart-card">
    <div class="item-info">
        <div class="item-name"><%= c.getName() %></div>
        <div class="item-price">₹ <%= c.getPrice() %> each</div>
        <div class="item-price"><b>Total: ₹ <%= itemTotal %></b></div>
    </div>

    <div class="item-actions">
        <form action="cart" method="post">
            <input type="hidden" name="itemId" value="<%= c.getItemId() %>">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="quantity" value="<%= c.getQuantity() - 1 %>">
            <button type="submit" class="qty-btn" <%= (c.getQuantity() <= 1 ? "disabled" : "") %>>-</button>
        </form>

        <span style="font-size:21px; font-weight:bold; color:#333;"><%= c.getQuantity() %></span>

        <form action="cart" method="post">
            <input type="hidden" name="itemId" value="<%= c.getItemId() %>">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="quantity" value="<%= c.getQuantity() + 1 %>">
            <button type="submit" class="qty-btn">+</button>
        </form>

        <form action="cart" method="post">
            <input type="hidden" name="itemId" value="<%= c.getItemId() %>">
            <input type="hidden" name="action" value="remove">
            <button type="submit" class="remove-btn">Remove</button>
        </form>
    </div>
</div>

<% } %>

<div style="text-align:center;">
    <form action="menu" method="get">
        <input type="hidden" name="restaurantId" value="<%= restaurantId %>">
        <button type="submit" class="add-more-btn">Add More Items</button>
    </form>
</div>

<% } else { %>

<div class="empty-cart">
    Your cart is empty 😞 <br><br>
    <form action="menu" method="get">
        <input type="hidden" name="restaurantId" value="<%= restaurantId %>">
        <button type="submit" class="add-more-btn">Add Items</button>
    </form>
</div>

<% } %>
</div>

<% if (cartItems != null && !cartItems.isEmpty()) { %>
<div class="cart-total">Grand Total: ₹ <%= total %></div>
<a href="checkout.jsp" class="checkout-btn">Proceed to Checkout</a>
<% } %>

</body>
</html>