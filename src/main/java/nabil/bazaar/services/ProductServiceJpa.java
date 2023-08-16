package nabil.bazaar.services;

import lombok.RequiredArgsConstructor;
import nabil.bazaar.domain.Category;
import nabil.bazaar.domain.Product;
import nabil.bazaar.exceptions.CategoryNotFoundException;
import nabil.bazaar.exceptions.ProductNotFoundException;
import nabil.bazaar.repositories.CategoryRepository;
import nabil.bazaar.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

/**
 * @author Ahmed Nabil
 */
@Service
@RequiredArgsConstructor
public class ProductServiceJpa implements ProductService{
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final PagingService pagingService;
    @Override
    public Page<Product> findAll(Integer pageNumber, Integer pageSize) {
        Pageable pageable = pagingService.getPageRequest(pageNumber, pageSize);
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> findAll(Integer pageNumber, Integer pageSize, Long categoryId) {
        if(categoryId == null) return findAll(pageNumber, pageSize);    // if no catId return to fetch all, otherwise search for specific category
        Pageable pageable = pagingService.getPageRequest(pageNumber, pageSize);
        return productRepository.findAllByCategories_Id(categoryId, pageable);
    }
    @Override
    public Page<Product> findAll(Integer pageNumber, Integer pageSize, Long categoryId, String search) {
        if(search == null) return findAll(pageNumber, pageSize, categoryId);
        Pageable pageable = pagingService.getPageRequest(pageNumber, pageSize);
        return productRepository.findAllByNameContainingIgnoreCase(search, pageable);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        Set<Category> safeCategories = getSafeCategories(product.getCategories());
        product.setCategories(safeCategories);
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Optional<Product> update(Long id, Product update) {
        Product product = productRepository.findById(id).orElseThrow(()->new ProductNotFoundException(id));
        product.setActive(update.isActive());
        product.setName(update.getName());
        product.setDescription(update.getDescription());
        product.setSku(update.getSku());
        product.setImageUrl(update.getImageUrl());
        product.setUnitPrice(update.getUnitPrice());
        Set<Category> updateCategories = getSafeCategories(update.getCategories());
        Iterator<Category> itr = product.getCategories().iterator();
        while(itr.hasNext()) {
            Category cat = itr.next();
            if(!updateCategories.contains(cat)) {
                itr.remove();
                cat.getProducts().remove(product);
            }
        }

        for(Category cat: updateCategories) {
            if(!product.getCategories().contains(cat)) {
                product.getCategories().add(cat);
                cat.getProducts().add(product);
            }
        }
        return Optional.of(productRepository.save(product));
    }

    private Set<Category> getSafeCategories(Set<Category> categories) {
        Set<Category> safeCategories = new HashSet<>();
        categories.forEach(cat -> {
            if(cat.getId() != null) {
                Category category = categoryRepository.findById(cat.getId()).orElseThrow(()->new CategoryNotFoundException(cat.getId()));
                safeCategories.add(category);
            } else {
                Optional<Category> categoryOptional = categoryRepository.findByNameIsLikeIgnoreCase(cat.getName());
                if(categoryOptional.isPresent()) {
                    safeCategories.add(categoryOptional.get());
                } else {
                    safeCategories.add(categoryRepository.save(cat));
                }
            }
        });
        return safeCategories;
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
