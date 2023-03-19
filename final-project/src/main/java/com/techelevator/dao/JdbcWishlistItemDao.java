package com.techelevator.dao;

import com.techelevator.model.WishlistItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcWishlistItemDao implements WishlistItemDao{

    private JdbcTemplate jdbcTemplate;
    @Autowired
    public JdbcWishlistItemDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    public JdbcWishlistItemDao(){};

    @Override
    public List<WishlistItem> getWishlistItems(int wishlistId, Principal principal){
        List<WishlistItem> wishlistItems = new ArrayList<>();
        String sql = "SELECT * FROM wishlist_item WHERE wishlist_id = ?";
        String username = principal.getName();
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, wishlistId, username);
        while (results.next()){
            wishlistItems.add(mapRowToWishlistItem(results));
        }
        return wishlistItems;
    }

    private WishlistItem mapRowToWishlistItem(SqlRowSet results) {
        WishlistItem wishlistItem = new WishlistItem();
        wishlistItem.setWishlistItemId(results.getInt("wishlist_item_id"));
        wishlistItem.setWishlistId(results.getInt("wishlist_id"));
        wishlistItem.setProductId(results.getInt("product_id"));
        return wishlistItem;
    }
}
