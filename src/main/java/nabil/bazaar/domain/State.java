package nabil.bazaar.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * @author Ahmed Nabil
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne()
    @JoinColumn(name = "country_id")
    private Country country;
}
