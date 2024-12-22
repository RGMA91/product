package com.example.product.mapper.strategy.implementation;

import com.example.product.entity.Product;
import com.example.product.mapper.strategy.DiscountStrategy;

public class CategoryDiscountStrategy implements DiscountStrategy {
    @Override
    public int calculateDiscount(Product product) {
        if (product.getCategory() == null) {
            return 0;
        }

        switch (product.getCategory()) {
            case "Electronics":
                return 15;
            case "Home & Kitchen":
                return 25;
            default:
                return 0;
        }
    }
}
