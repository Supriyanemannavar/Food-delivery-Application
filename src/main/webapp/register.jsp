<%@ page session="false" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register | TapFood</title>

<style>
    body {
        margin: 0;
        padding: 0;
        height: 100vh;
        font-family: 'Poppins', sans-serif;
        background: linear-gradient(135deg, #ff8800, #ff4d00);
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .card {
        background: #ffffff;
        width: 400px;
        padding: 30px;
        border-radius: 18px;
        box-shadow: 0px 6px 20px rgba(0,0,0,0.15);
        animation: fadeIn 0.8s ease-in-out;
    }

    @keyframes fadeIn {
        from { opacity: 0; transform: translateY(20px); }
        to   { opacity: 1; transform: translateY(0); }
    }

    h2 {
        text-align: center;
        color: #ff5200;
        margin-bottom: 15px;
    }

    input {
        width: 100%;
        padding: 12px;
        margin-top: 12px;
        border-radius: 8px;
        border: 1px solid #ddd;
        font-size: 15px;
        box-sizing: border-box;
        transition: 0.3s;
    }

    input:focus {
        border-color: #ff6a00;
        box-shadow: 0px 0px 6px rgba(255, 106, 0, 0.4);
        outline: none;
    }

    button {
        width: 100%;
        padding: 12px;
        margin-top: 18px;
        border: none;
        border-radius: 8px;
        background: #ff5200;
        color: white;
        font-size: 17px;
        cursor: pointer;
        transition: 0.3s;
    }

    button:hover {
        background: #e64500;
        transform: scale(1.03);
    }

    .link {
        text-align: center;
        margin-top: 12px;
        color: #ff5200;
        text-decoration: none;
        display: block;
    }

    .msg { color: green; text-align: center; margin-bottom: 5px; }
    .err { color: red; text-align: center; margin-bottom: 5px; }
</style>
</head>

<body>

<div class="card">

    <h2>Create Your TapFood Account 🍽️</h2>

    <% String msg = request.getParameter("msg");
       String err = request.getParameter("error");
       if(msg != null){ %>
        <p class="msg"><%= msg %></p>
    <% } else if(err != null) { %>
        <p class="err"><%= err %></p>
    <% } %>

    <form action="register" method="post">
        <input type="text" name="name" placeholder="Full Name" required>
        <input type="text" name="username" placeholder="Choose Username" required>
        <input type="password" name="password" placeholder="Create Password" required>
        <input type="email" name="email" placeholder="Email Address" required>
        <input type="text" name="address" placeholder="Your Address" required>

        <button type="submit">Create Account</button>
    </form>

    <a href="login.jsp" class="link">Already have an account? Login</a>
</div>

</body>
</html>
