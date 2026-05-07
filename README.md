#  Beer Guide — Guia de Cervejas Artesanais

API RESTful para gerenciamento de cervejas artesanais e cervejarias, desenvolvida como projeto da disciplina **Java Advanced** da **FIAP**.

---

##  Integrantes

| Nome | RM |
|------|----|
| Arthur Brito | RM 562085 |
| Luiz Felipe Flosi | RM 563197 |
| Pedro Brum | RM 561780 |
---

## Tecnologias Utilizadas

| Tecnologia | Versão |
|------------|--------|
| Java | 17 |
| Spring Boot | 3.2.5 |
| Spring Web MVC | — |
| Spring Data JPA | — |
| Spring Cache | — |
| Spring Actuator | — |
| H2 Database (in-memory) | — |
| Lombok | — |
| SpringDoc OpenAPI (Swagger) | 2.3.0 |
| Maven | — |

---

##  Arquitetura do Projeto

```
beer-guide/
└── src/main/java/fiap/com/br/beerguide/
    ├── BeerGuideApplication.java     # Classe principal (entry point)
    ├── config/
    │   └── SwaggerConfig.java        # Configuração do OpenAPI/Swagger
    ├── controller/
    │   ├── BeerController.java       # Endpoints de cervejas
    │   └── BreweryController.java    # Endpoints de cervejarias
    ├── data/
    │   └── MockData.java             # Dados iniciais carregados na inicialização
    ├── entity/
    │   ├── Beer.java                 # Entidade Cerveja
    │   └── Brewery.java              # Entidade Cervejaria
    ├── repository/
    │   ├── BeerRepository.java       # Repositório JPA de cervejas
    │   └── BreweryRepository.java    # Repositório JPA de cervejarias
    └── service/
        ├── BeerService.java          # Regras de negócio de cervejas
        └── BreweryService.java       # Regras de negócio de cervejarias
```

---

##  Como Executar

### Pré-requisitos

- Java 17+
- Maven 3.8+

### Rodando a aplicação

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/Cervejaria-java.git
cd Cervejaria-java/beer-guide

# Execute com Maven
mvn spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080`

---

##  Endpoints Disponíveis

### Cervejarias (`/breweries`)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/breweries` | Lista todas as cervejarias |
| `GET` | `/breweries/{id}` | Busca cervejaria por ID |
| `POST` | `/breweries` | Cadastra nova cervejaria |
| `PUT` | `/breweries/{id}` | Atualiza cervejaria |
| `DELETE` | `/breweries/{id}` | Remove cervejaria |

### Cervejas (`/beers`)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/beers` | Lista todas as cervejas |
| `GET` | `/beers/{id}` | Busca cerveja por ID |
| `GET` | `/beers/brewery/{id}` | Lista cervejas por cervejaria |
| `POST` | `/beers` | Cadastra nova cerveja |
| `PUT` | `/beers/{id}` | Atualiza cerveja |
| `DELETE` | `/beers/{id}` | Remove cerveja |

### Exemplos de Payload

**Criar Cervejaria:**
```json
{
  "name": "Cervejaria Bohemia",
  "country": "Brasil"
}
```

**Criar Cerveja:**
```json
{
  "name": "Red Ale Especial",
  "description": "Red Ale com notas de caramelo e lúpulo americano",
  "alcoholContent": 5.8,
  "harmonization": "Hambúrguer, batata frita",
  "breweryId": 1
}
```

---

##  Banco de Dados

A aplicação utiliza o banco **H2 em memória**, com dados de exemplo carregados automaticamente na inicialização:

| Cervejaria | País |
|------------|------|
| Serra Alta | Brasil |
| Rio Negro | Brasil |
| Vale Verde | Brasil |

Com as seguintes cervejas pré-cadastradas: Primavera IPA, Inverno Pale Ale, Noite Stout, Escura Porter, Lager do Sol e Weiss Dourada.

**Acesso ao Console H2:**
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:beerguide`
- Usuário: `sa`
- Senha: *(vazio)*

---

##  Observabilidade (Actuator)

| Endpoint | Descrição |
|----------|-----------|
| `GET /actuator/health` | Status da aplicação |
| `GET /actuator/info` | Informações da aplicação |
| `GET /actuator/metrics` | Métricas de performance |
| `GET /actuator/caches` | Caches ativos |

---

##  Documentação da API (Swagger)

Após subir a aplicação, acesse:

```
http://localhost:8080/swagger-ui.html
```

---

##  Funcionalidades Implementadas

- ✅ CRUD completo de **Cervejarias** e **Cervejas**
- ✅ Relacionamento `@ManyToOne` entre Beer e Brewery
- ✅ **Cache** com Spring Cache (`@Cacheable`, `@CacheEvict`)
- ✅ **Dados mock** carregados via `@PostConstruct`
- ✅ **Documentação automática** com Swagger/OpenAPI
- ✅ **Observabilidade** com Spring Actuator
- ✅ Banco H2 em memória para desenvolvimento

---

## Informações do Projeto

- **Disciplina:** Java Advanced
- **Instituição:** FIAP
- **Versão da aplicação:** 1.0.0
