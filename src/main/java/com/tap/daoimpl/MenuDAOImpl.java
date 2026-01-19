package com.tap.daoimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.MenuDAO;
import com.tap.model.Menu;
import com.tap.utility.DBConnection;

public class MenuDAOImpl implements MenuDAO {

    // SQL Queries
    private static final String INSERT_QUERY =
            "INSERT INTO Menu (restaurantId, name, description, price, isActive, imagePath) VALUES (?, ?, ?, ?, ?, ?,?)";

    private static final String GET_QUERY =
            "SELECT * FROM Menu WHERE menuid = ?";

    private static final String UPDATE_QUERY =
            "UPDATE Menu SET restaurantId=?, name=?, description=?, price=?, isActive=?, imagePath=? WHERE menuid=?";

    private static final String DELETE_QUERY =
            "DELETE FROM Menu WHERE menuid = ?";

    private static final String GET_ALL_QUERY =
            "SELECT * FROM Menu";

    private static final String GET_BY_RESTAURANT_QUERY =
            "SELECT * FROM Menu WHERE restaurantId = ?";

    // ADD MENU
    @Override
    public void addMenu(Menu menu) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_QUERY)) {

            ps.setInt(1, menu.getRestaurantId());
            ps.setString(2, menu.getName());
            ps.setString(3, menu.getDescription());
            ps.setDouble(4, menu.getPrice());
            ps.setBoolean(5, menu.getIsActive());
            ps.setString(6, menu.getImagePath());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // GET BY ID
    @Override
    public Menu getMenu(int menuid) {
        Menu menu = null;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(GET_QUERY)) {

            ps.setInt(1, menuid);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                menu = extractMenu(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menu;
    }

    // UPDATE
    @Override
    public void updateMenu(Menu menu) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_QUERY)) {

            ps.setInt(1, menu.getRestaurantId());
            ps.setString(2, menu.getName());
            ps.setString(3, menu.getDescription());
            ps.setDouble(4, menu.getPrice());
            ps.setBoolean(5, menu.getIsActive());
            ps.setString(6, menu.getImagePath());
            ps.setInt(7, menu.getMenuId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    @Override
    public void deleteMenu(int menuid) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_QUERY)) {

            ps.setInt(1, menuid);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // GET ALL MENUS
    @Override
    public List<Menu> getAllMenus() {
        List<Menu> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(GET_ALL_QUERY)) {

            while (rs.next()) {
                list.add(extractMenu(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // GET BY RESTAURANT ID
    @Override
    public List<Menu> getMenusByRestaurant(int restaurantId) {
        List<Menu> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(GET_BY_RESTAURANT_QUERY)) {

            ps.setInt(1, restaurantId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(extractMenu(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Convert ResultSet → Menu Object
    private Menu extractMenu(ResultSet rs) throws SQLException {
        return new Menu(
            rs.getInt("menuid"),
            rs.getInt("restaurantId"),
            rs.getString("name"),
            rs.getString("description"),
            rs.getDouble("price"),
            rs.getBoolean("isActive"),
            rs.getString("imagePath")
        );
    }
}