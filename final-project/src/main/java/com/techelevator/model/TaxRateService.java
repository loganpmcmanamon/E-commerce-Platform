package com.techelevator.model;

import com.techelevator.model.TaxRateResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Component
public class TaxRateService {
    public static String API_BASE_URL = "https://teapi.netlify.app/api/statetax";
    private RestTemplate restTemplate= new RestTemplate();

    public TaxRateService(){};

    public BigDecimal getTaxRate(String state){
        ResponseEntity<TaxRateResponseDto> response = restTemplate.getForEntity(API_BASE_URL + "?state=" + state,
                TaxRateResponseDto.class);
        BigDecimal taxrate = response.getBody().getTaxrate();
        return taxrate.divide(new BigDecimal(100));
    }
}
