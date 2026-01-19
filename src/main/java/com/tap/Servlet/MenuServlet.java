package com.tap.Servlet;

import java.io.IOException;
import java.util.List;

import com.tap.model.Menu;
import com.tap.daoimpl.MenuDAOImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String ridParam = req.getParameter("restaurantId");

        // If user clicks "Menu" from navbar → no restaurantId
        if (ridParam == null || ridParam.isEmpty()) {
            resp.sendRedirect("home");  // ✅ FIX
            return;
        }

        int rid = Integer.parseInt(ridParam);

        MenuDAOImpl daoImpl = new MenuDAOImpl();
        List<Menu> menuList = daoImpl.getMenusByRestaurant(rid);

        req.setAttribute("menus", menuList);

        // store for cart usage later
        req.getSession().setAttribute("restaurantId", rid);

        req.getRequestDispatcher("menu.jsp").forward(req, resp);
    }
}
