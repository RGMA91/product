package com.example.product.mapper;

import com.example.product.dto.ProductDto;
import com.example.product.entity.Product;
import com.example.product.mapper.strategy.DiscountStrategy;
import com.example.product.mapper.strategy.implementation.CategoryDiscountStrategy;
import com.example.product.mapper.strategy.implementation.CompositeDiscountStrategy;
import com.example.product.mapper.strategy.implementation.SkuDiscountStrategy;
import org.springframework.data.domain.Page;

import java.util.Arrays;

public class ProductMapper {

    private static final DiscountStrategy discountStrategy = new CompositeDiscountStrategy(Arrays.asList(
            new CategoryDiscountStrategy(),
            new SkuDiscountStrategy()
    ));

    public static final String PERCENT = "%";

    public static Page<ProductDto> productPageToProductDtoPage(Page<Product> productsPage) {
        return productsPage.map(ProductMapper::productToProductDto);
    }

    public static ProductDto productToProductDto(Product product) {
        if (product == null) {
            return null;
        }

        int discount = discountStrategy.calculateDiscount(product);
        Double priceAfterDiscount = calculatePriceAfterDiscount(product.getPrice(), discount);

        return ProductDto.builder()
                .sku(product.getSku())
                .price(product.getPrice())
                .description(product.getDescription())
                .category(product.getCategory())
                .discountPercentage(discount + PERCENT)
                .priceAfterDiscount(priceAfterDiscount)
                .build();
    }

    private static Double calculatePriceAfterDiscount(Double price, int discount) {
        return discount > 0 ? price - (price/100) * discount : price;
    }

}
