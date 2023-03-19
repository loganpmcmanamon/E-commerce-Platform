package com.techelevator.model;

import java.util.List;

public class Wishlist {

    private int wishlistId;
    private int userId;
    private String wishlistName;
    private List<Product> products;

    public Wishlist(int wishlistId, int userId, String wishlistName, List<Product> products) {
        this.wishlistId = wishlistId;
        this.userId = userId;
        this.wishlistName = wishlistName;
        this.products = products;
    }

    public int getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(int wishlistId) {
        this.wishlistId = wishlistId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getWishlistName() {
        return wishlistName;
    }

    public void setWishlistName(String wishlistName) {
        this.wishlistName = wishlistName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
