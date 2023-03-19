package com.techelevator.model;

public class WishlistItem {

    private int wishlistItemId;
    private int wishlistId;
    private int productId;

    public WishlistItem(int wishlistItemId, int wishlistId, int productId) {
        this.wishlistItemId = wishlistItemId;
        this.wishlistId = wishlistId;
        this.productId = productId;
    }

    public WishlistItem() {}

    public int getWishlistItemId() {
        return wishlistItemId;
    }

    public void setWishlistItemId(int wishlistItemId) {
        this.wishlistItemId = wishlistItemId;
    }

    public int getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(int wishlistId) {
        this.wishlistId = wishlistId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
