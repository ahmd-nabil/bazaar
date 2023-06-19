package nabil.bazaar.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ahmed Nabil
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "app_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // orders will be allowed for all (authenticated or not) so I won't tie it with App User
    // customer info
    private String firstName;
    private String lastName;
    private String email;

    // payment info
    private String cardNumber;
    private String cardExpiry;
    private String cvc;

    // Shipping address info
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Address shippingAddress;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Address billingAddress;

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderLine> orderLines = new ArrayList<>();
}
