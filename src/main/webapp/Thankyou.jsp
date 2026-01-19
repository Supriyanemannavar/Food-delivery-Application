<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order Successful</title>

<style>
    body{
        font-family: 'Poppins', sans-serif;
        background: #fff6f6;
        text-align: center;
        padding-top: 80px;
    }

    .card{
        width: 450px;
        background: white;
        margin: auto;
        padding: 35px;
        border-radius: 20px;
        box-shadow: 0 5px 20px rgba(0,0,0,0.1);
        animation: fadeIn 1s ease;
    }

    h1{
        font-size: 32px;
        color: #ff3b3b;
        margin-bottom: 10px;
    }

    p{
        font-size: 18px;
        color: #444;
        margin-bottom: 20px;
    }

    .success-icon{
        font-size: 80px;
        color: #28a745;
        animation: zoom 0.8s ease;
    }

    .btn{
        display: block;
        width: 250px;
        margin: 15px auto;
        padding: 14px;
        background:#ff3b3b;
        color: white;
        font-size: 18px;
        font-weight: 600;
        text-decoration: none;
        border-radius: 10px;
        transition: .3s;
    }
    .btn:hover{
        background:#cc1f1f;
    }

    @keyframes fadeIn{
        from{opacity: 0; transform: translateY(30px);}
        to{opacity: 1; transform: translateY(0);}
    }

    @keyframes zoom{
        from{transform: scale(0);}
        to{transform: scale(1);}
    }
</style>
</head>

<body>

<div class="card">
    <div class="success-icon">✔</div>
    <h1>Thank You! 🎉</h1>
    <p>Your order has been placed successfully.</p>
    <p>We’re preparing your delicious food 😋</p>

    <a href="home" class="btn">Back to Home</a>
</div>

</body>
</html>
