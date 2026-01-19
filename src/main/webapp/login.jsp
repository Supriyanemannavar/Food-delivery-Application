<%@ page session="false" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login | TapFood</title>

<style>
    body{
        margin:0;
        padding:0;
        font-family: 'Poppins', sans-serif;
        background: linear-gradient(135deg, #ff8a00, #e52e71);
        height:100vh;
        display:flex;
        justify-content:center;
        align-items:center;
    }

    .login-container{
        width:380px;
        background:white;
        border-radius:15px;
        padding:35px 30px;
        box-shadow:0px 8px 25px rgba(0,0,0,0.2);
        animation: fadeIn 1s ease;
    }

    @keyframes fadeIn{
        from { opacity:0; transform:translateY(20px); }
        to { opacity:1; transform:translateY(0); }
    }

    h2{
        text-align:center;
        color:#ff6f00;
        margin-bottom:20px;
        font-weight:700;
    }

    .input-field{
        margin-bottom:15px;
    }

    .input-field input{
        width:100%;
        padding:12px;
        border-radius:8px;
        border:1.5px solid #ddd;
        transition:0.3s;
        font-size:15px;
    }

    .input-field input:focus{
        border-color:#ff6f00;
        box-shadow:0 0 8px rgba(255,111,0,0.4);
        outline:none;
    }

    button{
        width:100%;
        padding:12px;
        font-size:17px;
        border:none;
        border-radius:8px;
        background:#ff6f00;
        color:white;
        cursor:pointer;
        transition:0.3s;
        letter-spacing:1px;
        font-weight:600;
        box-shadow:0px 5px 12px rgba(255,111,0,0.4);
    }

    button:hover{
        background:#e65100;
        box-shadow:0px 8px 17px rgba(255,111,0,0.6);
    }

    .small-text{
        text-align:center;
        margin-top:12px;
        font-size:14px;
    }

    .small-text a{
        color:#ff6f00;
        text-decoration:none;
        font-weight:600;
    }

    .msg{ color:green; text-align:center; font-size:15px; }
    .err{ color:red; text-align:center; font-size:15px; }
</style>

</head>
<body>

<div class="login-container">

    <h2>Welcome to TapFood</h2>

    <% String msg = request.getParameter("msg");
       String err = request.getParameter("error");
       if(msg != null){ %>
        <p class="msg"><%= msg %></p>
    <% } else if(err != null) { %>
        <p class="err"><%= err %></p>
    <% } %>

    <form action="login" method="post">

        <div class="input-field">
            <input type="text" name="username" placeholder="Enter Username" required>
        </div>

        <div class="input-field">
            <input type="password" name="password" placeholder="Enter Password" required>
        </div>

        <button type="submit">Login</button>
    </form>

    <p class="small-text">
        Don't have an account?
        <a href="register.jsp">Create one</a>
    </p>

</div>

</body>
</html>