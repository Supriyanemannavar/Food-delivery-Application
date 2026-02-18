package com.tap.Servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.tap.dao.UserDAO;
import com.tap.daoimpl.UserDAOImpl;
import com.tap.model.User;
import com.tap.utility.PasswordUtil;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDAO userDAO = new UserDAOImpl();
        
        // fetch by username and verify hashed password
        User user = userDAO.getUserByUsername(username);

        if (user != null && PasswordUtil.verifyPassword(password, user.getPassword())) {
            HttpSession session = request.getSession();
            session.setAttribute("username", user.getUsername());   // store username in session
            session.setAttribute("userId", user.getUserId());       // store id if needed
            session.setAttribute("role", user.getRole());           // useful for admin/student

            response.sendRedirect("home");   // logged in → go to home
        } else {
            response.sendRedirect("login.jsp?error=Invalid Username or Password");
        }
    }
}
