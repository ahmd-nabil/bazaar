package nabil.bazaar.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import nabil.bazaar.domain.Category;
import nabil.bazaar.domain.Product;
import nabil.bazaar.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Ahmed Nabil
 */
@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductService productService;

    @Autowired
    ObjectMapper objectMapper;

    Product product;
    @BeforeEach
    void setUp() {
        product = Product
                .builder()
                    .name("Lenovo L340")
                    .description("Lenovo laptop with Intel Core I9, 16GB of RAM, 512GB SSD, 1TB HDD")
                    .unitPrice(BigDecimal.valueOf(800.555))
                    .imageUrl("https://www.egytech.net/wp-content/uploads/2021/01/EGYTECH-L340-I5.jpg")
                    .unitsInStock(3)
                    .active(true)
                    .sku("default")
                    .categories(Collections.singleton(Category.builder().name("laptops").build()))
                .build();
    }

    @Test
    void findProductByIdSuccess() throws Exception {
        given(productService.findById(any())).willReturn(Optional.of(product));
        mockMvc.perform(get(ProductController.PRODUCT_API_ID, 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("Lenovo L340")));
    }

    @Test
    void findProductByIdFails() throws Exception {
        given(productService.findById(any())).willReturn(Optional.empty());
        mockMvc.perform(get(ProductController.PRODUCT_API_ID, 1))
                .andExpect(status().isNotFound());
    }

    @Test
    void findAllProducts() throws Exception {
        given(productService.findAll(any(), any(), any(), any())).willReturn(new PageImpl<>(Collections.singletonList(product)));
        mockMvc.perform(get(ProductController.PRODUCT_API))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()", is(1)));
    }

    @Test
    void saveProductSuccess() throws Exception {
        given(productService.save(any())).willReturn(product);
        mockMvc.perform(
                post(ProductController.PRODUCT_API)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated());
    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteProduct() {
    }
}