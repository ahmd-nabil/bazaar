package nabil.bazaar.services;

import lombok.RequiredArgsConstructor;
import nabil.bazaar.domain.Product;
import nabil.bazaar.exceptions.ProductNotFoundException;
import nabil.bazaar.repositories.CategoryRepository;
import nabil.bazaar.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Ahmed Nabil
 */
@Service
@RequiredArgsConstructor
public class ProductServiceJpa implements ProductService{
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    @Transactional
    public Product save(Product product) {
        Set<Category> safeCategories = new HashSet<>();
        product.getCategories().forEach(cat -> {
            if(cat.getId() == null) {
                Optional<Category> foundCategoryOptional = categoryRepository.findByNameIsLikeIgnoreCase(cat.getName());
                if(foundCategoryOptional.isPresent()) {
                    safeCategories.add(foundCategoryOptional.get());
                } else {
                    safeCategories.add(categoryRepository.save(cat));
                }
            }
        });
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> update(Long id, Product update) {
        if(productRepository.existsById(id)) {
            Product product = productRepository.findById(id).orElseThrow(()->new ProductNotFoundException(id));
            product.setActive(update.isActive());
            product.setCategories(update.getCategories());
            product.setName(update.getName());
            product.setDescription(update.getDescription());
            product.setSku(update.getSku());
            product.setImageUrl(update.getImageUrl());
            product.setUnitPrice(update.getUnitPrice());
            return Optional.of(productRepository.save(product));
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        if(productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
