package com.techelevator.controller;

import com.techelevator.dao.CartItemDao;
import com.techelevator.model.Cart;
import com.techelevator.model.CartItem;
import com.techelevator.model.CartService;
import com.techelevator.model.TaxRateService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RestController
@RequestMapping(path = "/cart")
@PreAuthorize("isAuthenticated()")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Cart getCart(Principal principal){
        return cartService.buildCart(principal);
    }

    @RequestMapping(path = "/items", method = RequestMethod.POST)
    public Cart add(@RequestBody CartItem item, Principal principal){
        if(item.getQuantity() <= 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantity must be a positive number.");
        }
        return cartService.addOrUpdateItem(item, principal);
    }




}
