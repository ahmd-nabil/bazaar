package nabil.bazaar.repositories;

import nabil.bazaar.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ahmed Nabil
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
