package nabil.bazaar.repositories;

import nabil.bazaar.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ahmed Nabil
 */
public interface AddressRepository extends JpaRepository<Address, Long> {
}
