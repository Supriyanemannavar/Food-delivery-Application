package com.tap.Servlet;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import com.tap.dao.UserDAO;
import com.tap.daoimpl.UserDAOImpl;
import com.tap.model.User;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String address = request.getParameter("address");

        UserDAO userDAO = new UserDAOImpl();

        // Check if user already exists
        User existingUser = userDAO.getUserByUsername(username);

        if (existingUser != null) {
            response.sendRedirect("register.jsp?error=Username already exists");
            return;
        }

        User user = new User();
        user.setName(name);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setAddress(address);

        userDAO.addUser(user);

        response.sendRedirect("login.jsp?msg=Registration Successful! Please login.");
    }
}
