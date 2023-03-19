package com.techelevator.dao;

import com.techelevator.model.WishlistItem;

import java.security.Principal;
import java.util.List;

public interface WishlistItemDao {

    public List<WishlistItem> getWishlistItems(int wishlistId, Principal principal);
}
