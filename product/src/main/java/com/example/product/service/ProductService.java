package com.example.product.service;

import com.example.product.dto.ProductDto;
import com.example.product.entity.Product;
import com.example.product.mapper.ProductMapper;
import com.example.product.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<ProductDto> getProducts(String category, String sortBy, boolean ascending, int page, int size) {
        Sort sort = ascending ? Sort.by(sortBy.toLowerCase()).ascending() : Sort.by(sortBy.toLowerCase()).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Product> productsPage = category != null ? productRepository.findByCategory(category, pageable) : productRepository.findAll(pageable);
        Page<ProductDto> productsDtoPage = ProductMapper.productPageToProductDtoPage(productsPage);
        return productsDtoPage;
    }

}
