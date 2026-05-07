package fiap.com.br.beerguide.controller;

import fiap.com.br.beerguide.entity.Beer;
import fiap.com.br.beerguide.service.BeerService;
import fiap.com.br.beerguide.service.BreweryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beers")
@RequiredArgsConstructor
@Tag(name = "Beers", description = "Gerenciamento de cervejas artesanais")
public class BeerController {

    private final BeerService beerService;
    private final BreweryService breweryService;

    public record BeerRequest(
            String name,
            String description,
            Double alcoholContent,
            String harmonization,
            Long breweryId
    ) {}

    @GetMapping
    @Operation(
            summary = "Lista todas as cervejas",
            description = "Retorna a lista completa de cervejas (resultado cacheado)"
    )
    public List<Beer> getAllBeers() {
        return beerService.getAllBeers();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Busca cerveja por ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cerveja encontrada"),
                    @ApiResponse(responseCode = "404", description = "Cerveja não encontrada")
            }
    )
    public Beer getBeerById(@PathVariable Long id) {
        return beerService.getBeerById(id);
    }

    @GetMapping("/brewery/{id}")
    @Operation(
            summary = "Lista cervejas por cervejaria",
            description = "Retorna todas as cervejas de uma cervejaria específica (resultado cacheado)",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Cervejaria não encontrada")
            }
    )
    public List<Beer> getBeersByBrewery(@PathVariable Long id) {
        breweryService.getBreweryById(id);
        return beerService.getBeersByBrewery(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Cadastra nova cerveja",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Cerveja criada com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Cervejaria não encontrada")
            }
    )
    public Beer createBeer(@RequestBody BeerRequest request) {
        var brewery = breweryService.getBreweryById(request.breweryId());
        return beerService.addBeer(
                Beer.builder()
                        .name(request.name())
                        .description(request.description())
                        .alcoholContent(request.alcoholContent())
                        .harmonization(request.harmonization())
                        .brewery(brewery)
                        .build()
        );
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualiza cerveja",
            description = "Invalida o cache automaticamente após atualização",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Atualizada com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Cerveja ou cervejaria não encontrada")
            }
    )
    public Beer updateBeer(@PathVariable Long id, @RequestBody BeerRequest request) {
        return beerService.updateBeer(
                id,
                request.name(),
                request.description(),
                request.alcoholContent(),
                request.harmonization(),
                request.breweryId()
        );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Remove cerveja",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Removida com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Cerveja não encontrada")
            }
    )
    public void deleteBeer(@PathVariable Long id) {
        beerService.deleteBeer(id);
    }

}