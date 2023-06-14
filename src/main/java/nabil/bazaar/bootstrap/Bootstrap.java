package nabil.bazaar.bootstrap;

import lombok.RequiredArgsConstructor;
import nabil.bazaar.csv.ProductCsvService;
import nabil.bazaar.domain.Category;
import nabil.bazaar.domain.Product;
import nabil.bazaar.repositories.CategoryRepository;
import nabil.bazaar.repositories.ProductRepository;
import nabil.bazaar.services.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * @author Ahmed Nabil
 */
@Component
@RequiredArgsConstructor
public class Bootstrap implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductCsvService productCsvService;
    private final ProductService productService;
    @Transactional
    @Override
    public void run(String... args) throws Exception {
        if(categoryRepository.count() <= 0) {
            populateCategories();
        }

        if(productRepository.count() <= 0) {
            populateProducts();
        }

        List<Product> products = productCsvService.getProductListFromCsv("products.csv");
        List<Product> products2 = productCsvService.getProductListFromCsv("books.csv");
        if(productRepository.count() <= 20) {
            products.forEach(productService::save);
        }
        if(productRepository.count() <= 50) {
            products2.forEach(productService::save);
        }

    }


    private void populateCategories() {
        categoryRepository.save(Category.builder().name("BOOKS").build());
    }

    private void populateProducts() {
        Category bookCategory = categoryRepository.findAll().stream().filter(cat -> cat.getName().equals("BOOKS")).toList().get(0);
        Product p1 = productRepository.save(
                Product.builder()
                    .sku("BOOK-TECH-1000")
                    .name("JavaScript - The Fun Parts")
                    .description("Learn JavaScript")
                    .imageUrl("assets/images/products/placeholder.png")
                    .active(true)
                    .unitsInStock(100)
                    .unitPrice(BigDecimal.valueOf(19.99))
                    .categories(Collections.singleton(bookCategory)).build());

        Product p2 = productRepository.save(
                Product.builder()
                        .sku("BOOK-TECH-1001")
                        .name("Spring Framework Tutorial")
                        .description("Learn Spring")
                        .imageUrl("assets/images/products/placeholder.png")
                        .active(true)
                        .unitsInStock(100)
                        .unitPrice(BigDecimal.valueOf(59.99))
                        .categories(Collections.singleton(bookCategory)).build());

        Product p3 = productRepository.save(
                Product.builder()
                        .sku("BOOK-TECH-1003")
                        .name("Internet of Things (IoT) - Getting Started")
                        .description("Learn IoT")
                        .imageUrl("assets/images/products/placeholder.png")
                        .active(true)
                        .unitsInStock(100)
                        .unitPrice(BigDecimal.valueOf(33.33))
                        .categories(Collections.singleton(bookCategory)).build());

        Product p4 = productRepository.save(
                Product.builder()
                        .sku("BOOK-TECH-1004")
                        .name("The Go Programming Language: A to Z")
                        .description("Learn GO")
                        .imageUrl("assets/images/products/placeholder.png")
                        .active(true)
                        .unitsInStock(100)
                        .unitPrice(BigDecimal.valueOf(88.88))
                        .categories(Collections.singleton(bookCategory)).build());
    }
}