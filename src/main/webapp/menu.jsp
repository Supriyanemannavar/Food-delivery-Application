<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, com.tap.model.Menu" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Restaurant Menu • Zomato Style</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600;700&display=swap" rel="stylesheet">

    <style>
        body {font-family: 'Poppins', sans-serif; background:white;}
        h2 {font-weight:700;margin-bottom:45px;color:#e23744;font-size:2.2rem;text-transform:uppercase;}
        .menu-card{border-radius:18px;overflow:hidden;background:#fff;border:1px solid #f0f0f0;transition:.35s;}
        .menu-card:hover{transform:translateY(-6px);box-shadow:0 12px 30px rgba(0,0,0,0.10);}
        .menu-card img{height:230px;width:100%;object-fit:cover;}
        .card-title{font-size:1.35rem;font-weight:600;}
        .price{font-size:1.15rem;font-weight:700;color:#e23744;}
        .btn-cart{width:100%;background:#e23744;color:white;border-radius:10px;border:none;font-weight:600;padding:10px;}
        .btn-cart:hover{background:#b81d30;transform:scale(1.03);}
        .back-btn{border-radius:10px;font-size:1.1rem;padding:10px 22px;}
    </style>
</head>

<body>

<div class="container mt-5">

    <center><h2>🍽 Explore Our Delicious Dishes</h2></center>

    <!-- Debug (Remove later after testing) -->
  
    <div class="row g-4">
        <%
            List<Menu> menus = (List<Menu>) request.getAttribute("menus");
            Integer restaurantId = (Integer) request.getAttribute("restaurantId");
            if (menus != null && !menus.isEmpty()) {
                for (Menu m : menus) {
        %>

        <div class="col-md-4">
            <div class="card menu-card shadow-sm">
                <img src="<%= m.getImagePath() %>" alt="Food">

                <div class="card-body">
                    <h5 class="card-title"><%= m.getName() %></h5>
                    <p class="card-text"><%= m.getDescription() %></p>
                    <p class="price">₹ <%= m.getPrice() %></p>

                    <form action="cart" method="post">
                        <input type="hidden" name="action" value="add">
                        <input type="hidden" name="itemId" value="<%= m.getMenuId() %>">
                        <input type="hidden" name="restaurantId" value="<%= restaurantId %>">
                        <input type="hidden" name="quantity" value="1">

                        <button type="submit" class="btn-cart">🛒 Add to Cart</button>
                    </form>
                </div>
            </div>
        </div>

        <% } } else { %>
            <h4 class="text-center text-danger mt-5">No Menu Available</h4>
        <% } %>

    </div>

    <!-- 🔥 Add More Items button (important for cart redirect back here) -->
    <div class="text-center mt-4">
        <form action="menu" method="get">
            <input type="hidden" name="restaurantId" value="${restaurantId}">
            <button class="btn btn-danger btn-lg">➕ Add More Items</button>
        </form>
    </div>

    <div class="text-center mt-4">
        <a href="home" class="btn btn-dark back-btn">🏠 Back to Home</a>
    </div>
</div>

</body>
</html>
