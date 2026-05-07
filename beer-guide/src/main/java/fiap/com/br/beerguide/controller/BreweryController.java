package fiap.com.br.beerguide.controller;

import fiap.com.br.beerguide.entity.Brewery;
import fiap.com.br.beerguide.service.BreweryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/breweries")
@RequiredArgsConstructor
@Tag(name = "Breweries", description = "Gerenciamento de cervejarias")
public class BreweryController {

    private final BreweryService breweryService;

    public record BreweryRequest(String name, String country) {}

    @GetMapping
    @Operation(
            summary = "Lista todas as cervejarias",
            description = "Retorna a lista completa de cervejarias (resultado cacheado)"
    )
    public List<Brewery> getAllBreweries() {
        return breweryService.getAllBreweries();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Busca cervejaria por ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cervejaria encontrada"),
                    @ApiResponse(responseCode = "404", description = "Cervejaria não encontrada")
            }
    )
    public Brewery getBreweryById(@PathVariable Long id) {
        return breweryService.getBreweryById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Cadastra nova cervejaria",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Cervejaria criada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos")
            }
    )
    public Brewery createBrewery(@RequestBody BreweryRequest request) {
        return breweryService.addBrewery(
                Brewery.builder()
                        .name(request.name())
                        .country(request.country())
                        .build()
        );
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualiza cervejaria",
            description = "Invalida o cache automaticamente após atualização",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Atualizada com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Cervejaria não encontrada")
            }
    )
    public Brewery updateBrewery(@PathVariable Long id, @RequestBody BreweryRequest request) {
        return breweryService.updateBrewery(id,
                Brewery.builder()
                        .name(request.name())
                        .country(request.country())
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Remove cervejaria",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Removida com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Cervejaria não encontrada")
            }
    )
    public void deleteBrewery(@PathVariable Long id) {
        breweryService.deleteBrewery(id);
    }

}
