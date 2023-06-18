package nabil.bazaar.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ahmed Nabil
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // only added country, state as entities because that what we need to calculate shipping
    // street, zipcode, ... enough to be strings for simplicity
    @ManyToOne
    private Country country;

    @ManyToOne
    private State state;

    private String street;
    private String city;
    private String zipcode;
}
