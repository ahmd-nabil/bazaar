package nabil.bazaar.repositories;

import nabil.bazaar.domain.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ahmed Nabil
 */
public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
}
