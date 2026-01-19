package com.tap.Servlet;

import java.io.IOException;

import com.tap.dao.MenuDAO;
import com.tap.daoimpl.MenuDAOImpl;
import com.tap.model.Cart;
import com.tap.model.CartItem;
import com.tap.model.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();

        // Load existing cart if available
        Cart cart = (Cart) session.getAttribute("cart");

        // Safely parse restaurantId
        String restaurantIdStr = req.getParameter("restaurantid");
        int restaurantid = 0;
        if (restaurantIdStr != null && !restaurantIdStr.isEmpty()) {
            restaurantid = Integer.parseInt(restaurantIdStr);
        }
        Integer currentRestaurantid = (Integer) session.getAttribute("restaurantid");

        // If cart not exists or restaurant changed → create new cart
        if (cart == null || currentRestaurantid == null || !currentRestaurantid.equals(restaurantid)) {
            cart = new Cart();
            session.setAttribute("cart", cart);
            session.setAttribute("restaurantid", restaurantid);
        }

        // Read action
        String action = req.getParameter("action");
        System.out.println("Action received: " + action);

        try {
            if (action != null) {
                switch (action) {
                    case "add":
                        addItemToCart(req, cart);
                        break;
                    case "update":
                        updateCartItem(req, cart);
                        break;
                    case "remove":
                        removeCartItem(req, cart);
                        break;
                    default:
                        System.out.println("Unknown action: " + action);
                }
            } else {
                System.out.println("Action parameter missing!");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Invalid number format in request parameters.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Redirect after processing
        resp.sendRedirect("cart.jsp"); // redirect to cart page
    }

    // ADD ITEM TO CART
    private void addItemToCart(HttpServletRequest req, Cart cart) throws ClassNotFoundException {
        String itemIdStr = req.getParameter("itemId");
        String quantityStr = req.getParameter("quantity");

        if (itemIdStr != null && quantityStr != null && !itemIdStr.isEmpty() && !quantityStr.isEmpty()) {
            int itemId = Integer.parseInt(itemIdStr);
            int quantity = Integer.parseInt(quantityStr);

            MenuDAO menuDAO = new MenuDAOImpl();
            Menu menuItem = menuDAO.getMenu(itemId);

            if (menuItem != null) {
                CartItem item = new CartItem(
                        menuItem.getMenuId(),
                        menuItem.getRestaurantId(),
                        menuItem.getName(),
                        quantity,
                        menuItem.getPrice()
                );

                cart.addItem(item);
                System.out.println("Item added: " + menuItem.getName());
            }
        } else {
            System.out.println("Missing itemId or quantity for add action.");
        }
    }

    // UPDATE ITEM QUANTITY
    private void updateCartItem(HttpServletRequest req, Cart cart) {
        String itemIdStr = req.getParameter("itemId");
        String quantityStr = req.getParameter("quantity");

        if (itemIdStr != null && quantityStr != null && !itemIdStr.isEmpty() && !quantityStr.isEmpty()) {
            int itemId = Integer.parseInt(itemIdStr);
            int quantity = Integer.parseInt(quantityStr);

            cart.updateItem(itemId, quantity);
            System.out.println("Item updated: " + itemId + " qty → " + quantity);
        } else {
            System.out.println("Missing itemId or quantity for update action.");
        }
    }

    // REMOVE ITEM
    private void removeCartItem(HttpServletRequest req, Cart cart) {
        String itemIdStr = req.getParameter("itemId");

        if (itemIdStr != null && !itemIdStr.isEmpty()) {
            int itemId = Integer.parseInt(itemIdStr);
            cart.removeItem(itemId);
            System.out.println("Item removed: " + itemId);
        } else {
            System.out.println("Missing itemId for remove action.");
        }
    }
}