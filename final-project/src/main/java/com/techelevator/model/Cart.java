package com.techelevator.model;

import java.math.BigDecimal;
import java.util.List;

public class Cart {

    private List<CartItem> items;
    private BigDecimal subtotal;
    private double taxrate;
    private BigDecimal total;

    public Cart(List<CartItem> items, BigDecimal subtotal, double taxrate, BigDecimal total) {
        this.items = items;
        this.subtotal = subtotal;
        this.taxrate = taxrate;
        this.total = total;
    }

    public Cart() {

    }

    public List<CartItem> getItems() {
        return items;
    }
    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }
    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public double getTaxrate() {
        return taxrate;
    }
    public void setTaxrate(double taxrate) {
        this.taxrate = taxrate;
    }

    public BigDecimal getTotal() {
        return total;
    }
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
