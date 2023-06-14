package nabil.bazaar.repositories;

import nabil.bazaar.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Ahmed Nabil
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByNameIsLikeIgnoreCase(String name);
}
