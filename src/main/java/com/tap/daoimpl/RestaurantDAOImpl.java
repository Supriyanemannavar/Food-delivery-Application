package com.tap.daoimpl;

import java.sql.*;
import java.util.*;
import com.tap.dao.RestaurantDAO;
import com.tap.model.Restaurant;
import com.tap.utility.DBConnection;

public class RestaurantDAOImpl implements RestaurantDAO {

    Connection con = DBConnection.getConnection();

    @Override
    public List<Restaurant> getAllRestaurants() {
        List<Restaurant> list = new ArrayList<>();

        try {
            String sql = "SELECT * FROM restaurant";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Restaurant r = new Restaurant();
                r.setRestaurantId(rs.getInt("restaurantId"));
                r.setName(rs.getString("name"));
                r.setAddress(rs.getString("address"));
                r.setPhone(rs.getString("phone"));
                r.setRating(rs.getDouble("rating"));
                r.setCuisineType(rs.getString("cuisineType"));
                r.setEta(rs.getInt("eta"));
                r.setImagePath(rs.getString("imagePath"));
                list.add(r);
            }

        } catch (Exception e) { e.printStackTrace(); }

        return list;
    }

    @Override
    public Restaurant getRestaurantById(int id) {
        try {
            String sql = "SELECT * FROM restaurant WHERE restaurantId=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Restaurant r = new Restaurant();
                r.setRestaurantId(rs.getInt("restaurantId"));
                r.setName(rs.getString("name"));
                r.setAddress(rs.getString("address"));
                r.setPhone(rs.getString("phone"));
                r.setRating(rs.getDouble("rating"));
                r.setCuisineType(rs.getString("cuisineType"));
                r.setEta(rs.getInt("eta"));
                r.setImagePath(rs.getString("imagePath"));
                return r;
            }
        } catch (Exception e) { e.printStackTrace(); }

        return null;
    }

    @Override
    public boolean addRestaurant(Restaurant restaurant) {
        try {
            String sql = "INSERT INTO restaurant(name,address,phone,rating,cuisineType,eta,imagePath) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, restaurant.getName());
            ps.setString(2, restaurant.getAddress());
            ps.setString(3, restaurant.getPhone());
            ps.setDouble(4, restaurant.getRating());
            ps.setString(5, restaurant.getCuisineType());
            ps.setInt(6, restaurant.getEta());
            ps.setString(7, restaurant.getImagePath());

            return ps.executeUpdate() > 0;

        } catch (Exception e) { e.printStackTrace(); }

        return false;
    }

    @Override
    public boolean updateRestaurant(Restaurant restaurant) {
        try {
            String sql = "UPDATE restaurant SET name=?,address=?,phone=?,rating=?,cuisineType=?,eta=?,imagePath=? WHERE restaurantId=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, restaurant.getName());
            ps.setString(2, restaurant.getAddress());
            ps.setString(3, restaurant.getPhone());
            ps.setDouble(4, restaurant.getRating());
            ps.setString(5, restaurant.getCuisineType());
            ps.setInt(6, restaurant.getEta());
            ps.setString(7, restaurant.getImagePath());
            ps.setInt(8, restaurant.getRestaurantId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) { e.printStackTrace(); }

        return false;
    }

    @Override
    public boolean deleteRestaurant(int id) {
        try {
            String sql = "DELETE FROM restaurant WHERE restaurantId=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) { e.printStackTrace(); }

        return false;
    }
}
