# Raízes do Nordeste — Back-end

Sistema de back-end para a rede de lanchonetes nordestinas "Raízes do Nordeste", desenvolvido como Projeto Multidisciplinar (Uninter) — trilha Back-end.

> Este repositório é parte de um estudo de caso acadêmico. A documentação de gestão do projeto (charter, backlog, ADRs) está no Confluence/Jira do projeto.

## Status

🚧 Em desenvolvimento — Sprint 0 concluída (kickoff, definição de stack). Ver o board de sprints para acompanhar o progresso.

## Stack tecnológica

| Camada | Tecnologia |
|---|---|
| Linguagem | Java |
| Framework | Spring Boot |
| Build tool | Maven |
| Banco de dados | PostgreSQL |

Justificativa completa das escolhas técnicas: ver ADR-001 na documentação do projeto.

## Estrutura do projeto

```
src/
  main/
    java/        # código-fonte da aplicação
    resources/   # application.yml, migrations, etc.
  test/
    java/        # testes automatizados
```

## Como rodar (em construção)

Pré-requisitos: JDK 17+, Maven 3.9+, PostgreSQL 15+.

```bash
# clonar o repositório
git clone <url-deste-repositorio>
cd raizes-do-nordeste-backend

# configurar variáveis de ambiente do banco (ver application.yml)

# rodar a aplicação
./mvnw spring-boot:run
```

## Documentação do projeto

- Modelagem (casos de uso, classes, DER): pasta `/docs` (em construção)
- Backlog e sprints: Jira (projeto SCRUM)
- Decisões de arquitetura (ADRs): Confluence

## Autoria

Desenvolvido por Yasmim Luana Ferreira da Silva, como parte da formação em Análise e Desenvolvimento de Sistemas (Uninter).

## Licença

Uso educacional — projeto de portfólio.
