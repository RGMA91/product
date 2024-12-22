package com.example.product.unit.service;

import com.example.product.dto.ProductDto;
import com.example.product.entity.Product;
import com.example.product.repository.ProductRepository;
import com.example.product.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import static com.example.product.helper.Helper.getProductsDtoPage;
import static com.example.product.helper.Helper.getProductsPage;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductService(productRepository);
    }

    @Test
    public void testGetProductsShouldReceiveParamsAndReturnProductDtoPageOK (){
        Page<Product> productPage = getProductsPage();
        when(productRepository.findByCategory(any(), any())).thenReturn(productPage);
        Page<ProductDto> expectedProductDtolist = getProductsDtoPage();
        assertEquals(expectedProductDtolist, productService.getProducts("Electronics", "sku", true, 0, 5));
    }

}
