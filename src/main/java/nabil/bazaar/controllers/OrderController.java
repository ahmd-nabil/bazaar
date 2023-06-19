package nabil.bazaar.controllers;

import lombok.RequiredArgsConstructor;
import nabil.bazaar.domain.Order;
import nabil.bazaar.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

/**
 * @author Ahmed Nabil
 */
@RestController
@RequiredArgsConstructor
@RequestMapping()
public class OrderController {

    private final OrderService orderService;
    public static final String ORDERS_API = "/api/v1/orders";
    public static final String ORDERS_API_ID = ORDERS_API + "/{id}";
    @GetMapping({ORDERS_API_ID})
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Optional<Order> orderOptional = orderService.findById(id);
        return orderOptional.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping(ORDERS_API)
    public ResponseEntity<?> saveOrder(@RequestBody Order order) {
        orderService.saveOrder(order);
        return ResponseEntity.created(URI.create(ORDERS_API + "/" + order.getId())).build();
    }

}
