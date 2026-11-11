# TaskFlow API — Gerenciamento de Tarefas em Java

API REST desenvolvida em Java 21 e Spring Boot, com persistência PostgreSQL, migrations Flyway e testes automatizados. Projeto de portfólio para demonstração de habilidades de backend.

## Stack
- Java 21, Spring Boot 3.5.7, Maven
- Spring Web, Spring Data JPA, Bean Validation, Actuator
- PostgreSQL 16, Flyway, Docker Compose
- JUnit 5, Mockito e MockMvc
- GitHub Actions (CI)

## Funcionalidades
- Criar, consultar, listar, atualizar e excluir tarefas
- Filtrar por status e paginar resultados
- Validar requisições e tratar erros (HTTP 400 e 404)
- Persistir tarefas no PostgreSQL
- Versionar esquema do banco com Flyway

## Requisitos
Java 21+ (JDK 25 também é compatível), Maven 3.9+, Docker com Compose.

## Executar localmente
1. Inicie o banco: `docker compose up -d`.
2. Execute: `mvn spring-boot:run`.
3. Acesse: `http://localhost:8080/api/v1/tasks`.
4. Para testes: `mvn clean test`.

Configurações padrão de desenvolvimento:
```text
DB_URL=jdbc:postgresql://localhost:5432/taskflow
DB_USERNAME=taskflow
DB_PASSWORD=taskflow
```
**Credenciais apenas para desenvolvimento local. Não use em produção.**

## Endpoints
| Método | Endpoint | Descrição |
| --- | --- | --- |
| POST | /api/v1/tasks | Criar tarefa |
| GET | /api/v1/tasks | Listar com paginação e filtro opcional por status |
| GET | /api/v1/tasks/{id} | Buscar por ID |
| PUT | /api/v1/tasks/{id} | Atualizar |
| DELETE | /api/v1/tasks/{id} | Excluir |

Filtros de exemplo: `GET /api/v1/tasks?status=PENDING&page=0&size=10`.

### Exemplo de criação
```bash
curl -X POST http://localhost:8080/api/v1/tasks \
  -H 'Content-Type: application/json' \
  -d '{"title":"Estudar Spring Boot","description":"Implementar API","dueDate":"2027-01-15","status":"PENDING"}'
```

## Estrutura
```text
src/main/java/dev/portfolio/taskflow/
  TaskflowApplication.java
  task/          # Entidade, DTOs, repository, service e controller
  common/        # Tratamento global de exceções
src/main/resources/
  application.yml
  db/migration/  # SQL versionado
src/test/java/    # Testes
.github/workflows/ci.yml  # Pipeline Maven
```

## Como importar no Eclipse
File > Import > Maven > Existing Maven Projects, selecione a pasta do projeto.

## Limitações
Não possui autenticação nem autorização, integração de testes com um PostgreSQL real ou deploy público. O GitHub Actions executa apenas os testes Maven; o banco precisa ser iniciado separadamente para rodar a aplicação. Não configure senhas reais no código.

## Próximos passos
Adicionar documentação OpenAPI/Swagger, testes com Testcontainers, autenticação e deploy.

## Licença
Uso educacional e de portfólio. Sem licença adicional de redistribuição.
