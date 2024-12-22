package com.example.product.mapper.strategy;

import com.example.product.entity.Product;

public interface DiscountStrategy {

    int calculateDiscount(Product product);

}
