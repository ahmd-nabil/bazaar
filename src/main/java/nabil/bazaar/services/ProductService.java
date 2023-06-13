package nabil.bazaar.services;

import nabil.bazaar.domain.Product;

import java.util.List;
import java.util.Optional;

/**
 * @author Ahmed Nabil
 */
public interface ProductService {
    List<Product> findAll();
    Optional<Product> findById(Long id);
    Product save(Product product);
    Optional<Product> update(Long id, Product update);
    boolean delete(Long id);
}
