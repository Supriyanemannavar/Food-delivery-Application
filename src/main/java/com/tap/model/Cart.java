package com.tap.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<CartItem> items = new ArrayList<>();

    // Add item to cart
    public void addItem(CartItem item) {
        for (CartItem c : items) {
            if (c.getItemId() == item.getItemId()) {   // if already exists increase quantity
                c.setQuantity(c.getQuantity() + item.getQuantity());
                return;
            }
        }
        items.add(item);
    }

    // Update item qty
    public void updateItem(int itemId, int quantity) {
        for (CartItem c : items) {
            if (c.getItemId() == itemId) {
                c.setQuantity(quantity);
                return;
            }
        }
    }

    // Remove item from cart
    public void removeItem(int itemId) {
        items.removeIf(c -> c.getItemId() == itemId);
    }

    // Get list of items
    public List<CartItem> getItems() {
        return items;
    }

    // Calculate total price
    public double getTotalAmount() {
        return items.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }
}
