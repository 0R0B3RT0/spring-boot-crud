# spring-boot-crud
Projeto de CRUD com Spring Boot

## Executar a aplicação
### Iniciar o banco de dados
```bash
sudo docker-compose up
```
### Compilar
```bash
mvn clean install
```
### Executar a aplicação
```bash
mvn spring-boot:run
```

## Stack
* Java 11
* Spring Boot
* Spring Data JPA
* Maven;
* Postgres;
* JUnit;

## Dependências
* [docker-compose](https://docs.docker.com/compose/install/#install-compose);
* [Lombok](https://projectlombok.org/)


## Changelog
- 0.0.1: Configuração da aplicação;
- 0.0.2: Implementação do fluso salvar pessoa #13;
- 0.0.3: Formatação do código com padrão google #17;
- 0.0.4: Criação de endpoint para salvar pessoa #14;
- 0.0.5: Configuração do hystrix #15;
- 0.0.6: Estrutura de validação #20;
- 0.0.7: Configuração Docker #6;
- 0.0.8: Funcionalidade atualizar pessoa #31;
