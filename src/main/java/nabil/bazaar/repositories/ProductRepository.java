package nabil.bazaar.repositories;

import nabil.bazaar.domain.Category;
import nabil.bazaar.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ahmed Nabil
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByCategories(Category category, Pageable pageable);
    Page<Product> findAllByCategories_Id(Long id, Pageable pageable);
    Page<Product> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
}
