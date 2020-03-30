# Smartphone API v1.1.0
[![Build Status](https://travis-ci.com/lericardolima/smartphone-api.svg?branch=develop)](https://travis-ci.com/lericardolima/smartphone-api)

API RESTful criada para o projeto Smartphone, um sistema para venda de smartphones.

### Ferramentas

*  [Java](https://www.java.com/pt_BR/)
*  [Spring Boot](https://spring.io/projects/spring-boot)
*  [Spring Data REST](https://spring.io/projects/spring-data-rest)
*  [PostgreSQL](https://www.postgresql.org/)
*  [ElephantSQL](https://www.elephantsql.com/)
*  [H2 Database](https://www.h2database.com/html/main.html)
*  [Gradle](https://gradle.org/)
*  [JUnit](https://junit.org/junit5/)
*  [Travis CI](https://travis-ci.com/)
*  [Heroku](https://dashboard.heroku.com/)
*  [Thymeleaf](https://www.thymeleaf.org/)
*  [Docker](https://www.docker.com/)
---
#### Heroku

  O Smartphone API está publicado no [Heroku](http://smartphone-api.herokuapp.com/api/browser/index.html).

#### Internacionalização

  O Smartphone API suporta os idiomas inglês (en-US), português (pt-BR) e espanhol (es-ES) nas mensagens de erro das requisições. Para receber as mensagens em algum destes idiomas, o parâmetro `Accept-Language` deve ser enviado no header das requisições.
  ```
  Accept-Language: pt-BR
  ```
  Quando não definido, o idioma padrão é o inglês (en-US).

---
### Configuração do ambiente

Siga as instruções para configurar o ambiente de desenvolvimento.

#### Banco de dados

  O Smartphone API lê as configurações de conexão com o banco de dados nas variáveis de ambiente. O nome do banco deve ser `smartphonedb`.
  ```
  DATABASE_URL=localhost:5432
  DATABASE_USERNAME=postgres
  DATABASE_PASSWORD=postgres
  ``` 

  Também é possível alterar as configurações no arquivo `application.properties`:
  ```
  spring.datasource.url=jdbc:postgresql://localhost:5432/smartphonedb
  spring.datasource.username=postgres
  spring.datasource.password=postgres
  ``` 

#### Iniciar o Smartphone API

  Na raiz do projeto, execute o comando:
  ```
  ./gradlew bootRun
  ```

  Após o fim da execução do comando, acesso o Smartphone API em [http://localhost:8080/api](http://localhost:8080/api).
  
#### Executar os testes

  Na raiz do projeto, execute o comando:
  ```
  ./gradlew test --info
  ```
  
#### Gerar build

  Na raiz do projeto, execute o comando:
  ```
  ./gradlew bootWar
  ```
  Este comando deve gerar um arquivo .war na pasta `biuld/libs`.

#### Executar em produção

  Execute o perfil de produção com Docker:
  ```
  docker-compose up
  ```
  Ou no .war gerado a partir do build do gradle:
  ```
  java -Dspring.profiles.active=prod -jar build/libs/smartphone-api-{versão}.war
  ```

  ## Contribuição
  
   **Ricardo de Lima Rocha**
  *  [Linkedin](https://www.linkedin.com/in/ricardo-de-lima-rocha/)
  *  [GitHub](https://github.com/lericardolima)
