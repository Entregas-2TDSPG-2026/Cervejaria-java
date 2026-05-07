package fiap.com.br.beerguide.service;

import fiap.com.br.beerguide.entity.Brewery;
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
public class BreweryService {

    private final BreweryRepository breweryRepository;

    @CacheEvict(value = {"breweries", "brewery"}, allEntries = true)
    public Brewery addBrewery(Brewery brewery) {
        return breweryRepository.save(brewery);
    }

    @Cacheable("breweries")
    public List<Brewery> getAllBreweries() {
        return breweryRepository.findAll();
    }

    @Cacheable(value = "brewery", key = "#id")
    public Brewery getBreweryById(Long id) {
        return breweryRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Brewery not found with id: " + id)
        );
    }

    @CacheEvict(value = {"breweries", "brewery"}, allEntries = true)
    public Brewery updateBrewery(Long id, Brewery updated) {
        Brewery existing = getBreweryById(id);
        existing.setName(updated.getName());
        existing.setCountry(updated.getCountry());
        return breweryRepository.save(existing);
    }

    @CacheEvict(value = {"breweries", "brewery"}, allEntries = true)
    public void deleteBrewery(Long id) {
        getBreweryById(id);
        breweryRepository.deleteById(id);
    }

}
