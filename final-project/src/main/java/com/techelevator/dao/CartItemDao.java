package com.techelevator.dao;

import com.techelevator.model.Cart;
import com.techelevator.model.CartItem;

import java.security.Principal;
import java.util.List;

public interface CartItemDao {

    public List<CartItem> getCartItems(Principal principal);

    public int add(int productID, int quantity, int userId);
}
