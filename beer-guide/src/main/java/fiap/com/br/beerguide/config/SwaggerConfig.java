package fiap.com.br.beerguide.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Beer Guide API",
                description = "Guia de Cervejas - Java Advanced FIAP",
                version = "1.0"
        )
)
public class SwaggerConfig {

}
