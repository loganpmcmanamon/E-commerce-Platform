package com.techelevator.dao;

import com.techelevator.model.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCartItemDao implements CartItemDao {
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public JdbcCartItemDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcCartItemDao(){};

    @Override
    public List<CartItem> getCartItems(Principal principal) {
        List<CartItem> items = new ArrayList<>();
        String sql = "SELECT cart_item_id, cart_item.user_id, product_id, quantity " +
                "FROM cart_item JOIN users ON users.user_id = cart_item.user_id " +
                "WHERE users.username = ?;";
        String username = principal.getName();
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
        while (results.next()){
            items.add(mapRowToCartItem(results));
        }
        return items;
    }

    @Override
    public int add(int productID, int quantity, int userId) {
        String sql = "INSERT INTO cart_item (user_id, product_id, quantity) " +
                "VALUES (?, ?, ?) " +
                "RETURNING cart_item_id;";
        int itemId = jdbcTemplate.queryForObject(sql, int.class, userId, productID, quantity);
        return itemId;
    }

    private CartItem mapRowToCartItem(SqlRowSet rowSet){
        CartItem cartItem = new CartItem();
        cartItem.setId(rowSet.getInt("cart_item_id"));
        cartItem.setUserId(rowSet.getInt("user_id"));
        cartItem.setProductId(rowSet.getInt("product_id"));
        cartItem.setQuantity(rowSet.getInt("quantity"));
        return cartItem;
    }
}
