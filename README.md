# API Rest: Gerenciamento de Aeroportos ✈️

Trabalho Prático da disciplina de Programação.

## 1. Objetivo do Projeto

Desenvolver uma API REST completa para gerenciar o cadastro de aeroportos espalhados pelo mundo, utilizando um conjunto de dados do OpenFlights. A API deve ser capaz de manipular esses dados em um banco de dados relacional e seguir todos os padrões e *endpoints* especificados no documento do trabalho.

## 2. Tecnologias Utilizadas (Stack Proposto)

Para atender aos requisitos do projeto, esta será a stack de desenvolvimento:

* **Linguagem:** Java 21
* **Framework:** Spring Boot 3
* **Banco de Dados:** MySQL
* **Build & Dependências:** Apache Maven
* **Testes:** JUnit 5, Mockito
* **Plugins Maven:** `maven-surefire-plugin` (Testes de Unidade) e `maven-failsafe-plugin` (Testes de Integração)

## 3. Como Configurar o Ambiente 

### Pré-requisitos
* Java 21 instalado
* Maven instalado (ou usar o wrapper `./mvnw` incluso)
* MySQL rodando na porta 3306

### Passo a Passo

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/Santos5-bh/api-gerenciamento-aeroportos.git](https://github.com/Santos5-bh/api-gerenciamento-aeroportos.git)
    cd api-gerenciamento-aeroportos
    ```

2.  **Configuração do Banco de Dados:**
    * Crie um banco de dados no MySQL chamado `aeroportos_db`.
    * No arquivo `src/main/resources/application.properties`, configure seu usuário e senha do MySQL.
    * **Importante:** Popule a tabela `aeroportos` importando o arquivo `airports.csv`.

3.  **Instale as dependências:**
    ```bash
    ./mvnw clean install
    ```

## 4. Como Executar a Aplicação ▶️

Para rodar a API localmente:

```bash
./mvnw spring-boot:run

A API estará disponível em: `http://localhost:8080/api/v1/aeroportos`

## 5. Como Executar os Testes 🧪

O projeto segue a estrutura padrão do Maven, separando testes de unidade e integração.

### Testes de Unidade (Lógica e Regras de Negócio)
Testa classes isoladas (ex: `AeroportoService`) usando Mockito.
```bash
./mvnw test
 
### Testes de Integração (API Completa)
Testa os endpoints da API interagindo com o banco de dados (ex: AeroportoControllerIT).
```bash
./mvnw verify