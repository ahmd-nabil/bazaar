package nabil.bazaar.repositories;

import nabil.bazaar.domain.Category;
import nabil.bazaar.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Ahmed Nabil
 */
@DataJpaTest
@ActiveProfiles("test")
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    Product product;
    Category category;

    @BeforeEach
    void setUp() {
        category = categoryRepository.save(Category.builder().name("Laptop").build());
        product = productRepository.save(
                Product.builder()
                        .name("Lenovo L340")
                        .description("Lenovo laptop with Intel Core I9, 16GB of RAM, 512GB SSD, 1TB HDD")
                        .unitPrice(BigDecimal.valueOf(800.555))
                        .imageUrl("https://www.egytech.net/wp-content/uploads/2021/01/EGYTECH-L340-I5.jpg")
                        .unitsInStock(3)
                        .active(true)
                        .sku("default")
                        .categories(Collections.singleton(category)).build()
        );
        System.out.println(" hi!! debugger is here");
    }


    @Test
    @Transactional
    void testCategoryWasSavedWithProductInProductsSet() {
        Category savedCategory = categoryRepository.findAll().get(0);
        assertEquals(savedCategory.getName(), category.getName());
        assertThat(savedCategory.getProducts().size()).isNotZero();
        System.out.println("Whatever to debug");
    }
}