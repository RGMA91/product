package com.example.product.unit.mapper;

import com.example.product.mapper.ProductMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.example.product.helper.Helper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProductMapperTest {

    @Test
    public void testProductToProductDtoShouldReturnProductDtoOK() {
        assertEquals(ProductMapper.productToProductDto(getProduct()), getProductDto());
    }

    @Test
    public void testproductPageToProductDtoShouldReturnProductPageOK() {
        assertEquals(ProductMapper.productPageToProductDtoPage(getProductsPage()), getProductsDtoPage());
    }

}
