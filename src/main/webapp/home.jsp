<%@ page import="java.util.*, com.tap.model.Restaurant" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>TapFood | Home</title>

    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">

    <style>
        body {
            background: #f8f9fa;
        }
        .navbar-brand {
            font-weight: bold;
            font-size: 24px;
            color: #ff5722 !important;
        }
        .restaurant-card {
            border-radius: 16px;
            overflow: hidden;
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
            transition: 0.3s;
            background: #fff;
        }
        .restaurant-card:hover {
            transform: translateY(-6px);
        }
        .restaurant-img {
            height: 200px;
            width: 100%;
            object-fit: cover;
        }
        .heading {
            text-align: center;
            margin-top: 30px;
            font-size: 30px;
            font-weight: 700;
        }
    </style>
</head>

<body>

<!-- 🔥 Top Navigation Bar -->
<nav class="navbar navbar-expand-lg navbar-light bg-white shadow-sm px-4">
    <a class="navbar-brand" href="home">TapFood</a>

    <div class="ms-auto">
        <a href="home" class="btn btn-outline-primary me-2">Home</a>
        <a href="menu" class="btn btn-outline-success me-2">Menu</a>
        <a href="cart.jsp" class="btn btn-outline-warning me-2">Cart</a>
        <a href="login.jsp" class="btn btn-primary">Login</a>
    </div>
</nav>

<!-- 🔥 Heading -->
<h2 class="heading">Available Restaurants</h2>

<div class="container mt-4">
    <div class="row justify-content-center">

        <%
            List<Restaurant> list = (List<Restaurant>) request.getAttribute("restaurants");

            if (list == null || list.isEmpty()) {
        %>

            <p class="text-center mt-4">No restaurants available.</p>

        <%
            } else {
                for (Restaurant r : list) {
        %>

        <!-- 🔥 Restaurant Card -->
        <div class="col-md-4 mb-4">
            <div class="restaurant-card">

                <img src="<%= r.getImagePath() %>" class="restaurant-img">

                <div class="p-3">
                    <h4><%= r.getName() %></h4>
                    <p class="text-muted"><%= r.getCuisineType() %></p>

                    <p>
                        ⭐ <b><%= r.getRating() %></b> &nbsp;
                        🕒 <%= r.getEta() %> mins
                    </p>

                    <p class="text-secondary"><%= r.getAddress() %></p>

                    <a href="menu?restaurantId=<%= r.getRestaurantId() %>"
                       class="btn btn-primary w-100">
                        View Menu
                    </a>
                </div>

            </div>
        </div>

        <%
                }
            }
        %>

    </div>
</div>

</body>
</html>
