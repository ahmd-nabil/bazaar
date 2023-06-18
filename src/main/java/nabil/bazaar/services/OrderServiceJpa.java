package nabil.bazaar.services;

import lombok.RequiredArgsConstructor;
import nabil.bazaar.domain.Order;
import nabil.bazaar.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Ahmed Nabil
 */
@Service
@RequiredArgsConstructor
public class OrderServiceJpa implements OrderService{

    private final OrderRepository orderRepository;

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }
}
