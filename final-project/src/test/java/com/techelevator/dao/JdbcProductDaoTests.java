package com.techelevator.dao;

import com.techelevator.model.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcProductDaoTests extends BaseDaoTests{

    protected static final Product PRODUCT_1 =new Product(1, "SKU-001", "Product 1", "Product description 1.", 14.99, "Product001.jpg");

    private JdbcProductDao dao;

    @Before
    public void setup(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        dao = new JdbcProductDao(jdbcTemplate);
    }

    @Test
    public void getAllProducts_returns_all_products(){
        List<Product> products = dao.getAllProducts();

        Assert.assertNotNull(products);
        Assert.assertEquals(7, products.size());
        Assert.assertEquals(PRODUCT_1.getProductId(), products.get(0).getProductId());
    }

    @Test
    public void getProduct_by_id_returns_correct_product(){
        Product actualProduct = dao.getProductById(PRODUCT_1.getProductId());
        assertProductsMatch(PRODUCT_1, actualProduct);
    }

    private void assertProductsMatch(Product expected, Product actual){
        Assert.assertEquals(expected.getProductId(), actual.getProductId());
        Assert.assertEquals(expected.getProductSKU(), actual.getProductSKU());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        Assert.assertEquals(expected.getPrice(), actual.getPrice(), 0.01);
        Assert.assertEquals(expected.getImageName(), actual.getImageName());

    }



}
