package com.example.product.helper;

import com.example.product.dto.ProductDto;
import com.example.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public class Helper {

    public static Page<Product> getProductsPage() {
        List<Product> productList = List.of(
                new Product(1L, "SKU0001", 100.00, "description", "Electronics"),
                new Product(3L, "SKU0003", 100.00, "description", "Home & Kitchen"),
                new Product(3L, "SKU0005", 100.00, "description", "Home & Kitchen"),
                new Product(4L, "SKU0015", 100.00, "description", "Electronics"),
                new Product(5L, "SKU0020", 100.00, "description", "Accessories"));
        return new PageImpl<>(productList);
    }

    public static Page<ProductDto> getProductsDtoPage() {
        List<ProductDto> expectedProductDtolist = List.of(
                new ProductDto("SKU0001", 100.00, "description", "Electronics", "15%", 85.00),
                new ProductDto("SKU0003", 100.00, "description", "Home & Kitchen", "25%", 75.00),
                new ProductDto("SKU0005", 100.00, "description", "Home & Kitchen", "30%", 70.00),
                new ProductDto("SKU0015", 100.00, "description", "Electronics", "30%", 70.00),
                new ProductDto("SKU0020", 100.00, "description", "Accessories", "0%", 100.00));
        return new PageImpl<>(expectedProductDtolist);
    }

    public static ProductDto getProductDto() {
        return new ProductDto("SKU0001", 100.00, "description", "Electronics", "15%", 85.00);
    }

    public static Product getProduct() {
        return new Product(1L, "SKU0001", 100.00, "description", "Electronics");
    }
}
