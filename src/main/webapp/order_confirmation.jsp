<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Checkout Page</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>

<body class="bg-light">

<div class="container mt-5">
    <h2 class="text-center mb-4">Checkout</h2>

    <!-- Only One Form -->
    <form action="checkout" method="post" class="border p-4 bg-white shadow rounded">

        <!-- Address -->
        <div class="mb-3">
            <label class="form-label">Delivery Address</label>
            <textarea name="address" class="form-control" rows="3" required></textarea>
        </div>

        <!-- Payment Method (DROPDOWN) -->
        <div class="mb-3">
            <label class="form-label">Payment Method</label>
            <select name="paymentMethod" class="form-select" required>
                <option value="" disabled selected>Select Payment Method</option>
                <option value="Cash on Delivery">Cash on Delivery</option>
                <option value="UPI">UPI</option>
                <option value="Card Payment">Card Payment</option>
            </select>
        </div>

        <!-- Submit -->
        <button type="submit" class="btn btn-success w-100">Place Order</button>

    </form>
</div>

</body>
</html>