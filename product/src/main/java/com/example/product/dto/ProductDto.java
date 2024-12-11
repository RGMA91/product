package com.example.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProductDto {

    private String sku;

    private Double price;

    private String description;

    private String category;

    @JsonProperty("discount_percentage")
    private String discountPercentage;

    @JsonProperty("price_after_discount")
    private Double priceAfterDiscount;

}
