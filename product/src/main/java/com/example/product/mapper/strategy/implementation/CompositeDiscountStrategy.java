package com.example.product.mapper.strategy.implementation;

import com.example.product.entity.Product;
import com.example.product.mapper.strategy.DiscountStrategy;

import java.util.List;

public class CompositeDiscountStrategy implements DiscountStrategy {

    private final List<DiscountStrategy> strategies;

    public CompositeDiscountStrategy(List<DiscountStrategy> strategies) {
        this.strategies = strategies;
    }

    @Override
    public int calculateDiscount(Product product) {
        return strategies.stream()
                .mapToInt(strategy -> strategy.calculateDiscount(product))
                .max()
                .orElse(0);
    }
}