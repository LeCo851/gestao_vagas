# Gestão de Vagas

Este projeto é uma API REST para gerenciamento de vagas de emprego, desenvolvida com Java e Spring Boot. O sistema permite o cadastro de empresas, candidatos e vagas, além de gerenciar autenticação e autorização.

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA**
- **Spring Security** (com JWT)
- **PostgreSQL**
- **Docker**
- **Swagger / OpenAPI** (Documentação da API)
- **Lombok**
- **JUnit 5** (Testes)

## ⚙️ Como Executar o Projeto

### Pré-requisitos

- Java 17 instalado
- Maven instalado
- Docker instalado

### Passo a Passo

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/seu-usuario/gestao_vagas.git
   cd gestao_vagas
   ```

2. **Suba o banco de dados com Docker:**

   O projeto utiliza um arquivo `docker-compose.yml` para configurar o PostgreSQL.

   ```bash
   docker-compose up -d
   ```

   Isso iniciará o banco de dados na porta `5433` com as credenciais configuradas.

3. **Compile e execute a aplicação:**

   ```bash
   mvn spring-boot:run
   ```

   A aplicação estará rodando em `http://localhost:8080`.

## 🧪 Testes

O projeto conta com testes automatizados para garantir a qualidade do código:

- **Testes Unitários:** Verificam o funcionamento isolado de componentes e regras de negócio.
- **Testes de Integração:** Validam o fluxo completo das requisições, incluindo a interação com o banco de dados e controllers.

Para executar os testes:

```bash
mvn test
```

## 📚 Documentação da API (Swagger)

Após iniciar a aplicação, você pode acessar a documentação interativa da API através do Swagger UI:

- **URL:** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## 🛠️ Funcionalidades Principais

- **Candidatos:**
    - Cadastro de perfil
    - Autenticação
    - Aplicação para vagas

- **Empresas:**
    - Cadastro de empresa
    - Autenticação
    - Criação de vagas

- **Segurança:**
    - Autenticação via Token JWT
    - Criptografia de senhas com BCrypt
