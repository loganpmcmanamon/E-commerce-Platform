package com.techelevator.controller;

import com.techelevator.dao.ProductDao;
import com.techelevator.model.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    private final ProductDao productDao;

    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Product getProductById(@PathVariable int id){
        return productDao.getProductById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Product> getProductByNameOrSku(@RequestParam(required = false) String name,
                                               @RequestParam(required = false) String sku){
        if(name == null && sku == null){
            return productDao.getAllProducts();        }
        return productDao.getProductByNameOrSku(name, sku);
    }

}
