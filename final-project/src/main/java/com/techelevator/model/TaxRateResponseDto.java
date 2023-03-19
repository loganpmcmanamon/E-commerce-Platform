package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class TaxRateResponseDto {
    @JsonProperty("salesTax")
    private BigDecimal taxrate;
    public BigDecimal getTaxrate(){
        return taxrate;
    }

    public void setTaxrate(BigDecimal taxrate) {
        this.taxrate = taxrate;
    }
}
