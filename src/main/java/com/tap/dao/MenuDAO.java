package com.tap.dao;

import java.util.List;
import com.tap.model.Menu;

public interface MenuDAO {

    // Add a new menu item
    void addMenu(Menu menu);

    // Get a menu item by its ID
    Menu getMenu(int menuid);

    // Update an existing menu item
    void updateMenu(Menu menu);

    // Delete a menu item by its ID
    void deleteMenu(int menuid);

    // Get all menu items
    List<Menu> getAllMenus();

    // Get all menu items by restaurant ID
    List<Menu> getMenusByRestaurant(int restaurantId);
}