package nabil.bazaar.repositories;

import nabil.bazaar.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ahmed Nabil
 */
public interface CountryRepository extends JpaRepository<Country, Integer> {
}
