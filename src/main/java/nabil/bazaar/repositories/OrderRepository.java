package nabil.bazaar.repositories;

import nabil.bazaar.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ahmed Nabil
 */

public interface OrderRepository extends JpaRepository<Order, Long> {
}
