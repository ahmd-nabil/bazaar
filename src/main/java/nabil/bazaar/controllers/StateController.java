package nabil.bazaar.controllers;

import lombok.RequiredArgsConstructor;
import nabil.bazaar.domain.State;
import nabil.bazaar.repositories.StateRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Ahmed Nabil
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/states")
public class StateController {
    private final StateRepository stateRepository;

    @GetMapping("/{countryId}")
    public List<State> getStatesByCountry(@PathVariable Integer countryId) {
        return stateRepository.findAllByCountry_Id(countryId);
    }
}
