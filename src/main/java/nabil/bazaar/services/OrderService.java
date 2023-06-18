package nabil.bazaar.services;

import nabil.bazaar.domain.Order;

import java.util.Optional;

/**
 * @author Ahmed Nabil
 */
public interface OrderService {
    Optional<Order> findById(Long id);
    Order saveOrder(Order order);
}
