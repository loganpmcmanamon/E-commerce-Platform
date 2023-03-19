package com.techelevator.model;

import com.techelevator.dao.CartItemDao;
import com.techelevator.dao.ProductDao;
import com.techelevator.dao.UserDao;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;


@Component
public class CartService {
    private CartItemDao cartItemDao;
    private ProductDao productDao;
    private UserDao userDao;
    private TaxRateService taxRateService;

    public CartService(CartItemDao cartItemDao, ProductDao productDao, UserDao userDao, TaxRateService taxRateService) {
        this.cartItemDao = cartItemDao;
        this.productDao = productDao;
        this.userDao = userDao;
        this.taxRateService = taxRateService;
    }

    public BigDecimal buildItemSubtotal(CartItem item){
        Product product = productDao.getProductById(item.getProductId());
        BigDecimal price = BigDecimal.valueOf(product.getPrice());
        return price.multiply(BigDecimal.valueOf(item.getQuantity()));
    }

    public BigDecimal buildCartSubtotal(List<CartItem> items){
        BigDecimal subtotal = BigDecimal.ZERO;
        for(CartItem item : items){
            subtotal = subtotal.add(buildItemSubtotal(item));
        }
        return subtotal;
    }
    
    public Cart buildCart(Principal principal){
        Cart cart = new Cart();
        BigDecimal subtotal = buildCartSubtotal(cartItemDao.getCartItems(principal));
        User user = getPrincipalUser(principal);
        
        cart.setItems(cartItemDao.getCartItems(principal));
        cart.setSubtotal(subtotal);
        cart.setTaxrate(taxRateService.getTaxRate(user.getStateCode()).doubleValue());
        cart.setTotal(subtotal.add(subtotal.multiply(taxRateService.getTaxRate(user.getStateCode()))));

        return cart;
    }

    public Cart addOrUpdateItem(CartItem item, Principal principal){
        Cart cart = new Cart();
        User user = getPrincipalUser(principal);
        if(item != null){
            cartItemDao.add(item.getProductId(), item.getQuantity(), user.getId());
            cart = buildCart(principal);

        }
        return cart;
    }

    private User getPrincipalUser(Principal principal) {
        String username = principal.getName();
        return userDao.findByUsername(username);
    }

}
