<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Checkout</title>

<style>
    body {
        margin: 0;
        padding: 0;
        font-family: Arial, sans-serif;
        background: #f8f8f8;
    }

    .navbar {
        background: #ff4d4d;
        padding: 18px;
        text-align: center;
        color: white;
        font-size: 24px;
        font-weight: bold;
    }

    .checkout-box {
        width: 90%;
        max-width: 450px;
        background: white;
        margin: 40px auto;
        padding: 25px;
        border-radius: 12px;
        box-shadow: 0px 4px 10px rgba(0,0,0,0.1);
    }

    label {
        font-weight: bold;
        margin-top: 10px;
        display: block;
    }

    input, select {
        width: 100%;
        padding: 12px;
        margin-top: 8px;
        border: 1px solid #ccc;
        border-radius: 8px;
        font-size: 16px;
    }

    button {
        width: 100%;
        padding: 14px;
        background: #ff4d4d;
        border: none;
        color: white;
        font-size: 18px;
        margin-top: 20px;
        border-radius: 10px;
        cursor: pointer;
    }

    button:hover {
        background: #e63e3e;
    }
</style>

</head>

<body>

<div class="navbar">Checkout</div>

<div class="checkout-box">
    <form action="checkout" method="post">

        <label>Delivery Address</label>
        <input type="text" name="address" placeholder="Enter your delivery address" required>

        <label>Payment Method</label>
        <select name="paymentMethod" required>
            <option value="COD">Cash on Delivery</option>
            <option value="UPI">UPI</option>
            <option value="Card">Credit/Debit Card</option>
        </select>

        <button type="submit">Place Order</button>
    </form>
</div>

</body>
</html>
