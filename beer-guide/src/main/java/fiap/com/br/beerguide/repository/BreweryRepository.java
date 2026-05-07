package fiap.com.br.beerguide.repository;

import fiap.com.br.beerguide.entity.Brewery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BreweryRepository extends JpaRepository<Brewery, Long> {
}
