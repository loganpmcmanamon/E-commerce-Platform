package com.techelevator.dao;

import com.techelevator.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcProductDao implements ProductDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcProductDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()){
            Product product = mapRowToProduct(results);
            products.add(product);
        }
        return products;
    }

    @Override
    public List<Product> getProductByNameOrSku(String name, String sku) {
        String sql = "SELECT * FROM product WHERE name ILIKE ? " +
                "OR product_sku ILIKE ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, "%"+name+"%", "%"+sku+"%");
        return resultsAsList(results);

    }


    @Override
    public Product getProductById(int productId) {
        String sql = "SELECT * FROM product WHERE product_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, productId);
        if(results.next()){
            return mapRowToProduct(results);
        }

        return null;
    }

    private Product mapRowToProduct(SqlRowSet rowSet){
        Product product = new Product();
        product.setProductId(rowSet.getInt("product_id"));
        product.setProductSKU(rowSet.getString("product_sku"));
        product.setName(rowSet.getString("name"));
        product.setPrice(rowSet.getDouble("price"));
        product.setDescription(rowSet.getString("description"));
        product.setImageName(rowSet.getString("image_name"));
        return product;
    }

    private List<Product> resultsAsList (SqlRowSet rowSet){
        List<Product> list = new ArrayList<>();
        while (rowSet.next()){
            Product product = mapRowToProduct(rowSet);
            list.add(product);
        }
        return list;
    }
}
