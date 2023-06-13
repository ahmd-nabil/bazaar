package nabil.bazaar.repositories;

import nabil.bazaar.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ahmed Nabil
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}
