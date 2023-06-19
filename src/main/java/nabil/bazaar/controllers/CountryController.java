package nabil.bazaar.controllers;

import lombok.RequiredArgsConstructor;
import nabil.bazaar.domain.Country;
import nabil.bazaar.repositories.CountryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Ahmed Nabil
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/countries")
public class CountryController {
    private final CountryRepository countryRepository;
    @GetMapping()
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }
}
