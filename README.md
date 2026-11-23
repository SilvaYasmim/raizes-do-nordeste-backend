# Raízes do Nordeste — Back-end

Sistema de back-end para a rede de lanchonetes nordestinas "Raízes do Nordeste", desenvolvido como Projeto Multidisciplinar (Uninter) — trilha Back-end.

> Este repositório é parte de um estudo de caso acadêmico. A documentação de gestão do projeto (charter, backlog, ADRs) está no Confluence/Jira do projeto.

## Status

🚧 Entrega do projeto multidisciplinar (trilha back end) com implementacao inicial concluida, cobrindo os endpoints principais de catalogo e pedidos. As demais funcionalidades modeladas ficam previstas para evolucoes futuras.

## Stack tecnológica

| Camada | Tecnologia |
|---|---|
| Linguagem | Java 21 |
| Framework | Spring Boot 4.1 |
| Build tool | Maven (com wrapper mvnw) |
| Banco de dados | PostgreSQL 18 |
| ORM | Spring Data JPA|
| Documentação e especificação de API| springdoc openapi (Swagger)|

As escolhas tecnicas do back end seguem o registro ADR 001 na documentacao do projeto. O Java com Spring Boot foi escolhido porque ja faz parte do dominio da equipe, o que reduz o risco no prazo curto, oferece um ecossistema amplo e atende bem a necessidades como robustez, escalabilidade, tolerancia a falhas e auditoria, mesmo sendo mais verboso. O PostgreSQL foi adotado por ser um banco relacional confiável, que ajuda a guardar formatos variados de dados vindos de integracoes externas sem exigir estrutura rigida demais. Ja o Maven entrou por ter curva de aprendizado menor que o Gradle, facilitar a manutencao e ser padrao em projetos Java corporativos.

## Estrutura do projeto

```
docs/
  evidencias/    # prints dos testes e do Swagger UI
  postman/       # colecoes de requisicoes para teste
  sql/           # scripts SQL e dados iniciais de teste
  confluence/    # documentacao de suporte do projeto
  jira/          # prints dos scruns e processos de gestao do projeto
  openapi.jason
src/
  main/
    java/br/com/raizes/raizes_api/
      catalogo/  # entidades, DTOs, repositories, service e controller do catalogo
      cliente/   # entidade e repository de cliente
      loja/      # entidade e repository de loja
      pedido/    # entidades, enums, DTOs, repository, service e controller de pedidos
      RaizesApiApplication.java
    resources/
      application.properties
  test/
    java/        # testes automatizados
```

## Como rodar (em construção)

Pré-requisitos: Pre-requisitos: JDK 21, Maven (com wrapper mvnw), PostgreSQL 18.

```bash
# clonar o repositorio
git clone https://github.com/SilvaYasmim/raizes-do-nordeste-backend.git
cd raizes-do-nordeste-backend

# criar o banco raizes no PostgreSQL e configurar a variavel de ambiente DB_PASSWORD (ver application.properties)

# rodar a aplicacao
./mvnw spring-boot:run
```
Depois que a aplicacao subir e criar as tabelas, carregue o script docs/sql/dados-teste.sql no banco raizes. A documentacao do Swagger fica em http://localhost:8080/swagger-ui.html.

## Documentação do projeto

- Requisitos, modelagem (casos de uso, classes, DER) e ADRs: Confluence (espaco Raizes do Nordeste)
- Decisoes de arquitetura (ADR 001): Confluence (pagina Decisao de Arquitetura no espaco Raizes do Nordeste)
- Backlog e sprints: Jira (projeto SCRUM)
- Artefatos tecnicos e testes: pasta /docs (sql, postman, evidencias, confluence, jira e arquivo openapi.json)

## Endpoints implementados

- GET /api/lojas/{id_loja}/catalogo: consulta os produtos, precos e disponibilidade da unidade informada
- POST /api/pedidos: cria um pedido, valida os dados e o estoque da unidade, congela o preco unitario e da baixa no estoque

## Autoria

Desenvolvido por Yasmim Luana Ferreira da Silva, como parte da formação em Análise e Desenvolvimento de Sistemas (Uninter).

## Licença

Uso educacional — projeto de portfólio.
