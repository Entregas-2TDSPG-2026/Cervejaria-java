package fiap.com.br.beerguide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BeerGuideApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeerGuideApplication.class, args);
    }

}
