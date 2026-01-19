package com.tap.model;

public class CartItem {

    private int itemId;
    private int restaurantid;
    private String name;
    private int quantity;
    private double price;

    public CartItem(int itemId, int restaurantid, String name, int quantity, double price) {
        this.itemId = itemId;
        this.restaurantid = restaurantid;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public int getItemId() {
        return itemId;
    }

    public int getRestaurantid() {
        return restaurantid;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public double getTotalPrice() {
        return price * quantity;
    }
}
