package nabil.bazaar.repositories;

import nabil.bazaar.domain.Address;
import nabil.bazaar.domain.Order;
import nabil.bazaar.domain.OrderLine;
import nabil.bazaar.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
/**
 * @author Ahmed Nabil
 */
@SpringBootTest
class OrderRepositoryTest {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    OrderLineRepository orderLineRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CountryRepository countryRepository;
    @Autowired
    StateRepository stateRepository;
    Order order;
    List<OrderLine> orderLines;
    List<Product> products;
    Address address;
    @BeforeEach
    public void setUp() {
        // setting up order lines for order
        products = productRepository.findAll();
        OrderLine orderLine1 = OrderLine.builder()
                .product(products.get(1))
                .quantity(2)
                .unitPrice(products.get(1).getUnitPrice())  // price should be snapshot of the current price of product(in case product price changes)
                .build();
        OrderLine orderLine2 = OrderLine.builder()
                .product(products.get(2))
                .quantity(3)
                .unitPrice(products.get(2).getUnitPrice())  // price should be snapshot of the current price of product(in case product price changes)
                .build();
        orderLines = Arrays.asList(orderLine1, orderLine2);

        // setting up both shipping&billing address to the same address
        address = Address.builder()
                .country(countryRepository.findAll().get(0))
                .state(stateRepository.findAllByCountry_Id(1).get(0))
                .build();
        order = Order.builder()
                .firstName("Ahmed")
                .lastName("Nabil")
                .shippingAddress(address)
                .billingAddress(address)
                .orderLines(orderLines)
                .build();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Test
    void testSaveOrder() {
        Order savedOrder = orderRepository.save(order);
        assertThat(savedOrder.getOrderLines().size()).isEqualTo(2);
        assertThat(savedOrder.getOrderLines().get(0).getId()).isNotNull();
        assertThat(savedOrder.getOrderLines().get(1).getId()).isNotNull();
    }
}