package fiap.com.br.beerguide.data;

import fiap.com.br.beerguide.entity.Beer;
import fiap.com.br.beerguide.entity.Brewery;
import fiap.com.br.beerguide.service.BeerService;
import fiap.com.br.beerguide.service.BreweryService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MockData {

    private final BreweryService breweryService;
    private final BeerService beerService;

    @PostConstruct
    public void init() {

        Brewery serraAlta = breweryService.addBrewery(Brewery.builder()
                .name("Serra Alta")
                .country("Brasil")
                .build());

        Brewery rioNegro = breweryService.addBrewery(Brewery.builder()
                .name("Rio Negro")
                .country("Brasil")
                .build());

        Brewery valeVerde = breweryService.addBrewery(Brewery.builder()
                .name("Vale Verde")
                .country("Brasil")
                .build());

        beerService.addBeer(Beer.builder()
                .name("Primavera IPA")
                .description("IPA encorpada com notas cítricas e amargor equilibrado")
                .alcoholContent(6.5)
                .harmonization("Frango grelhado, queijos")
                .brewery(serraAlta)
                .build());

        beerService.addBeer(Beer.builder()
                .name("Inverno Pale Ale")
                .description("Pale Ale suave com aroma floral e final seco")
                .alcoholContent(5.2)
                .harmonization("Peixes grelhados, saladas")
                .brewery(serraAlta)
                .build());

        beerService.addBeer(Beer.builder()
                .name("Noite Stout")
                .description("Stout robusta com notas de café e chocolate amargo")
                .alcoholContent(7.2)
                .harmonization("Chocolate amargo, carne assada")
                .brewery(rioNegro)
                .build());

        beerService.addBeer(Beer.builder()
                .name("Escura Porter")
                .description("Porter cremosa com sabor de caramelo e baunilha")
                .alcoholContent(6.0)
                .harmonization("Costela, queijos curados")
                .brewery(rioNegro)
                .build());

        beerService.addBeer(Beer.builder()
                .name("Lager do Sol")
                .description("Lager refrescante, leve e bem carbonatada")
                .alcoholContent(4.8)
                .harmonization("Petiscos, saladas leves")
                .brewery(valeVerde)
                .build());

        beerService.addBeer(Beer.builder()
                .name("Weiss Dourada")
                .description("Weiss alemã com notas de banana e cravo, espuma generosa")
                .alcoholContent(5.4)
                .harmonization("Linguiça, pão de queijo")
                .brewery(valeVerde)
                .build());
    }

}
