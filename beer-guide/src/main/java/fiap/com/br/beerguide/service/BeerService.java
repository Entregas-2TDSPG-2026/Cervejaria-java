package fiap.com.br.beerguide.service;

import fiap.com.br.beerguide.entity.Beer;
import fiap.com.br.beerguide.entity.Brewery;
import fiap.com.br.beerguide.repository.BeerRepository;
import fiap.com.br.beerguide.repository.BreweryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BeerService {

    private final BeerRepository beerRepository;
    private final BreweryRepository breweryRepository;

    @CacheEvict(value = {"beers", "beer", "beersByBrewery"}, allEntries = true)
    public Beer addBeer(Beer beer) {
        return beerRepository.save(beer);
    }

    @Cacheable("beers")
    public List<Beer> getAllBeers() {
        return beerRepository.findAll();
    }

    @Cacheable(value = "beer", key = "#id")
    public Beer getBeerById(Long id) {
        return beerRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Beer not found with id: " + id)
        );
    }

    @Cacheable(value = "beersByBrewery", key = "#breweryId")
    public List<Beer> getBeersByBrewery(Long breweryId) {
        return beerRepository.findByBreweryId(breweryId);
    }

    @CacheEvict(value = {"beers", "beer", "beersByBrewery"}, allEntries = true)
    public Beer updateBeer(Long id, String name, String description, Double alcoholContent, String harmonization, Long breweryId) {

        Beer existing = beerRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Beer not found with id: " + id)
        );

        Brewery brewery = breweryRepository.findById(breweryId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Brewery not found with id: " + breweryId)
        );

        existing.setName(name);

        existing.setDescription(description);

        existing.setAlcoholContent(alcoholContent);

        existing.setHarmonization(harmonization);

        existing.setBrewery(brewery);

        return beerRepository.save(existing);
    }

    @CacheEvict(value = {"beers", "beer", "beersByBrewery"}, allEntries = true)
    public void deleteBeer(Long id) {
        beerRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Beer not found with id: " + id)
        );
        beerRepository.deleteById(id);
    }

}