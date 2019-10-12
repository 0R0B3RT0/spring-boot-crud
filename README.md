# spring-boot-crud
Projeto de CRUD com Spring Boot

## Executar a aplicação
### Crie o banco de dados
```bash
createdb -U postgres spring-boot-crud
```
### Compilar
```bash
mvn clean install
```

## Stack
* Java 11
* Spring Boot
* Spring Data JPA
* Maven;
* Postgres;
* JUnit;

## Changelog
- 0.0.1: Configuração da aplicação;
- 0.0.2: Implementação do fluso salvar pessoa #13;
- 0.0.3: Formatação do código com padrão google #17;
- 0.0.4: Criação de endpoint para salvar pessoa #14;
- 0.0.5: Configuração do hystrix #15;
