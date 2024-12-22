package com.example.product.mapper.strategy.implementation;

import com.example.product.entity.Product;
import com.example.product.mapper.strategy.DiscountStrategy;

public class SkuDiscountStrategy implements DiscountStrategy {

    @Override
    public int calculateDiscount(Product product) {
        if (product.getSku() != null && product.getSku().endsWith("5")) {
            return 30;
        }
        return 0;
    }
}
