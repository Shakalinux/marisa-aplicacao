# Documentação do Projeto MariasaAplicacao

## Índice
1. [Introdução](#introdução)
2. [Estrutura do Projeto](#estrutura-do-projeto)
3. [Classes e Funções](#classes-e-funções)
    - [ClienteDao](#clienteda)
    - [BaseConexao](#baseconexao)
    - [Produto](#produto)
    - [Pessoa](#pessoa)
    - [GlobalController](#globalcontroller)
    - [Area](#area)
    - [Cliente](#cliente)
    - [Funcionario](#funcionario)
4. [Execução do Projeto](#execução-do-projeto)

## Introdução
Este documento descreve a estrutura e as funcionalidades do projeto **MariasaAplicacao**, uma aplicação Spring Boot que gerencia clientes, funcionários e produtos.

## Estrutura do Projeto
O projeto está organizado da seguinte forma:
- **`dao`**: Contém as classes de acesso a dados.
- **`model`**: Contém as classes de modelo que representam as entidades do sistema.
- **`controller`**: Contém as classes de controle que gerenciam as requisições HTTP.
- **`MariasaAplicacaoApplication`**: Classe principal que inicia a aplicação Spring Boot.

## Classes e Funções

### ClienteDao
Classe responsável pelo acesso aos dados dos clientes.

#### Métodos:
- **`cadastrarCliente(Cliente cliente)`**: Cadastra um novo cliente no banco de dados.
    - **Parâmetros**: `Cliente cliente` - objeto contendo os dados do cliente.
    - **Descrição**: Insere os dados do cliente nas tabelas `pessoa` e `clientes`, utilizando transações para garantir a integridade dos dados.

- **`listarClientes()`**: Retorna uma lista de todos os clientes cadastrados.
    - **Retorno**: `List<Cliente>` - lista de objetos `Cliente`.

### BaseConexao
Classe responsável por fornecer a conexão com o banco de dados.

#### Métodos:
- **`getConexao()`**: Retorna uma conexão com o banco de dados.
    - **Retorno**: `Connection` - objeto de conexão com o banco de dados.
    - **Descrição**: Utiliza o `DriverManager` para obter uma conexão com o banco de dados MySQL.

### Produto
Classe que representa um produto.

#### Atributos:
- **`idProduto`**: Identificador do produto.
- **`nome`**: Nome do produto.
- **`preco`**: Preço do produto.
- **`categoria`**: Categoria do produto.
- **`descricao`**: Descrição do produto.

#### Métodos:
- **Getters e setters** para todos os atributos.
- **`toString()`**: Retorna uma representação em string do produto.

### Pessoa
Classe que representa uma pessoa.

#### Atributos:
- **`idPessoa`**: Identificador da pessoa.
- **`nome`**: Nome da pessoa.
- **`cpf`**: CPF da pessoa.
- **`telefone`**: Telefone da pessoa.

#### Métodos:
- **Getters e setters** para todos os atributos.

### GlobalController
Classe que gerencia as requisições HTTP.

#### Métodos:
- **`listar(Model model)`**: Lista todos os clientes, funcionários e produtos.
    - **Parâmetros**: `Model model` - objeto utilizado para adicionar atributos ao modelo.
    - **Retorno**: `String` - nome da view a ser renderizada.

- **`cadastroCliente(Model model)`**: Exibe a página de cadastro de cliente.
    - **Parâmetros**: `Model model` - objeto utilizado para adicionar atributos ao modelo.
    - **Retorno**: `String` - nome da view a ser renderizada.

- **`cadastrarCliente(Cliente cliente)`**: Cadastra um novo cliente.
    - **Parâmetros**: `Cliente cliente` - objeto contendo os dados do cliente.
    - **Retorno**: `String` - redireciona para a página inicial.

- **`cadastrarFuncionario(Model model)`**: Exibe a página de cadastro de funcionário.
    - **Parâmetros**: `Model model` - objeto utilizado para adicionar atributos ao modelo.
    - **Retorno**: `String` - nome da view a ser renderizada.

- **`cadastrarFuncionario(Funcionario funcionario, @RequestParam Long idArea)`**: Cadastra um novo funcionário.
    - **Parâmetros**:
        - `Funcionario funcionario` - objeto contendo os dados do funcionário.
        - `@RequestParam Long idArea` - identificador da área do funcionário.
    - **Retorno**: `String` - redireciona para a página inicial.

### Area
Classe que representa uma área.

#### Atributos:
- **`idArea`**: Identificador da área.
- **`nome`**: Nome da área.

#### Métodos:
- **Getters e setters** para todos os atributos.
- **`toString()`**: Retorna uma representação em string da área.

### Cliente
Classe que representa um cliente, herda de `Pessoa`.

#### Atributos:
- **`idCliente`**: Identificador do cliente.
- **`email`**: Email do cliente.

#### Métodos:
- **Getters e setters** para todos os atributos.

### Funcionario
Classe que representa um funcionário, herda de `Pessoa`.

#### Atributos:
- **`idFuncionario`**: Identificador do funcionário.
- **`matricula`**: Matrícula do funcionário.
- **`area`**: Área do funcionário.
- **`salario`**: Salário do funcionário.

#### Métodos:
- **Getters e setters** para todos os atributos.

## Execução do Projeto
Para executar o projeto, utilize o comando:
```bash
mvn spring-boot:run
