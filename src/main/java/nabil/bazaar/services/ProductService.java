package nabil.bazaar.services;

import nabil.bazaar.domain.Product;
import org.springframework.data.domain.Page;

import java.util.Optional;

/**
 * @author Ahmed Nabil
 */
public interface ProductService {
    Page<Product> findAll(Integer pageNumber, Integer pageSize);
    Optional<Product> findById(Long id);
    Product save(Product product);
    Optional<Product> update(Long id, Product update);
    boolean delete(Long id);
}
