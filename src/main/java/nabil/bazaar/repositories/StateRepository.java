package nabil.bazaar.repositories;

import nabil.bazaar.domain.Country;
import nabil.bazaar.domain.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ahmed Nabil
 */
@Repository
public interface StateRepository extends JpaRepository<State, Integer> {
    List<State> findAllByCountry(Country country);
    List<State> findAllByCountry_Id(Integer countryId);
}
