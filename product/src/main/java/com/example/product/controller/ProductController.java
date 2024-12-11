package com.example.product.controller;

import com.example.product.dto.ProductDto;
import com.example.product.service.ProductService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Page.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content) })
    @GetMapping("/catalog")
    public ResponseEntity<Page<ProductDto>> getProductsCatalog (
            @RequestParam (required = false) String category,
            @RequestParam (required = false, defaultValue = "id") String sortBy,
            @RequestParam (defaultValue = "true") boolean ascending,
            @RequestParam (defaultValue = "0") int page,
            @RequestParam (defaultValue = "5") int size) {
        Page<ProductDto> productDtoPage = productService.getProducts(category, sortBy, ascending, page, size);
        return ResponseEntity.ok(productDtoPage);
    }

}
