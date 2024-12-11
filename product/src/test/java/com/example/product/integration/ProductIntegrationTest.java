package com.example.product.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetProductsCatalogByCategorySortedBySkuAscendingShouldReturnCategoryAscendingProductsPageSortedOK() throws Exception {

        mockMvc.perform(get("/api/product/catalog")
                        .param("category", "Electronics")
                        .param("sortBy", "sku")
                        .param("ascending", "true")
                        .param("page", "0")
                        .param("size", "5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(5)))
                .andExpect((ResultMatcher) jsonPath("$.content[0].sku", is("SKU0001")))
                .andExpect((ResultMatcher) jsonPath("$.content[4].sku", is("SKU0009")));

    }

    @Test
    public void testGetProductsCatalogByCategorySortedBySkuDescendingShouldReturnProductsCategoryDescendingPageSortedOK() throws Exception {

        mockMvc.perform(get("/api/product/catalog")
                        .param("category", "Electronics")
                        .param("sortBy", "sku")
                        .param("ascending", "false")
                        .param("page", "0")
                        .param("size", "5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(5)))
                .andExpect((ResultMatcher) jsonPath("$.content[0].sku", is("SKU0029")))
                .andExpect((ResultMatcher) jsonPath("$.content[4].sku", is("SKU0011")));

    }

    @Test
    public void testGetProductsCatalogByCategorySortedByIncorrectValueShouldReturnBadRequestKO() throws Exception {

        mockMvc.perform(get("/api/product/catalog")
                        .param("category", "Accesories")
                        .param("sortBy", "INCORRECT_VALUE")
                        .param("ascending", "true")
                        .param("page", "0")
                        .param("size", "5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}
