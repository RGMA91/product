package com.example.product.mapper;

import com.example.product.dto.ProductDto;
import com.example.product.entity.Product;
import org.springframework.data.domain.Page;

public class ProductMapper {

    public static final String DISCOUNT_15 = "Electronics";
    public static final String DISCOUNT_25 = "Home & Kitchen";
    public static final String DISCOUNT_30 = "5";
    public static final String PERCENT = "%";

    public static Page<ProductDto> productPageToProductDtoPage(Page<Product> productsPage) {
        return productsPage.map(ProductMapper::productToProductDto);
    }

    public static ProductDto productToProductDto(Product product) {
        if (product == null) {
            return null;
        }

        int discount = calculateDiscount(product);
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

    private static Integer calculateDiscount(Product product) {
        int discount = 0;
        if (product.getCategory() != null && product.getCategory().equals(DISCOUNT_15)) {
            discount = 15;
        }
        if (product.getCategory() != null && product.getCategory().equals(DISCOUNT_25)) {
            discount = 25;
        }
        if (product.getSku() != null && product.getSku().endsWith(DISCOUNT_30)){
            discount = 30;
        }
        return discount;
    }

}
