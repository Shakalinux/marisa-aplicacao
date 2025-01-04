# Descrição do Projeto: Sistema de Gerenciamento

Este projeto é uma aplicação web construída com Spring Boot para gerenciamento de clientes, funcionários e produtos. O sistema permite cadastro, edição e visualização de dados, com interfaces intuitivas para interagir com as informações. A seguir, são apresentadas as principais funcionalidades e telas do sistema.

## Funcionalidades Principais

### 1. Cadastro e Autenticação de Usuário
- O sistema permite o cadastro de clientes e funcionários, com autenticação segura.
- Cadastro de Clientes e Funcionários.
- Edição e Exclusão de dados de clientes e funcionários.

### 2. Gestão de Produtos
- Cadastro e gerenciamento de produtos, incluindo informações como nome, preço, categoria e descrição.
- Edição e exclusão de produtos.

### 3. Relatórios e Dashboard
- O sistema possui uma interface para exibir relatórios, como total de funcionários, produtos e vendas.

## Funcionalidades de Navegação e Interface

Abaixo estão algumas imagens que mostram a interface do sistema para o gerenciamento de dados:

### Tela Inicial
![Tela Inicial](https://i.postimg.cc/d3MJzqwR/1.png)

### Cadastro de Cliente
![Cadastro de Cliente](https://i.postimg.cc/C1nFPZTL/2.png)

### Cadastro de Funcionário
![Cadastro de Funcionário](https://i.postimg.cc/vZXHcFtM/3.png)

### Cadastro de Produto
![Cadastro de Produto](https://i.postimg.cc/BnJnSQDM/4.png)

### Visualização de Dados
![Visualização de Dados](https://i.postimg.cc/yNb1ymGb/5.png)

### Dashboard 
![Edição de Produto](https://i.postimg.cc/bvJYnCmn/6.png)

## Estrutura do Projeto

A aplicação segue a arquitetura **MVC** (Model-View-Controller) e utiliza as seguintes tecnologias:

- **Spring Boot**: Framework principal para o desenvolvimento da aplicação.
- **Spring Security**: Para gerenciar a autenticação e autorização de usuários.
- **Spring Data JPA**: Para interação com o banco de dados e operações CRUD.
- **Thymeleaf**: Motor de templates para renderização de páginas HTML.
- **Base64**: Para codificar e exibir imagens (como avatares e imagens de tarefas).

## Estrutura de Pastas

- **Controller**: Contém os controllers responsáveis por lidar com as requisições HTTP e a lógica de negócios.
- **Model**: Contém as classes que representam os dados do sistema, como `Cliente`, `Funcionario`, `Produto`, etc.
- **Repository**: Responsável pela persistência de dados utilizando o Spring Data JPA.
- **View (Templates)**: Contém as páginas HTML renderizadas com Thymeleaf.

## Tecnologias Utilizadas

- **Spring Boot**: Framework para simplificar o desenvolvimento de aplicações Java.
- **Spring Security**: Para segurança da aplicação, incluindo autenticação de usuários.
- **Spring Data JPA**: Para fácil acesso a dados no banco de dados relacional.
- **Thymeleaf**: Para renderizar a interface do usuário com HTML dinâmico.
- **Base64**: Para representar imagens binárias em formato textual, como avatares de perfil e imagens de tarefas.

## Banco de dados
+------------------+          +---------------------+           +------------------+
|     pessoa       |          |      clientes       |           |      areas       |
+------------------+          +---------------------+           +------------------+
| - idPessoa: INT  |<---------| - idCliente: INT     |           | - idArea: INT    |
| - nome: VARCHAR  |          | - email: VARCHAR     |           | - nome: VARCHAR  |
| - cpf: VARCHAR   |          | - idPessoa: INT      |           +------------------+
| - telefone: VARCHAR|        |                     |
+------------------+          +---------------------+           +------------------+
         ^                               ^                      |    |    |    |
         |                               |                      |    |    |
         |                               |                      |    |    |
         |                               +----------------------+    |    |
         |                                                        |    |
         |                                                        |    |
         |   +------------------+     +--------------------+     |    |
         |   |   funcionarios    |     |     produtos       |     |    |
         |   +------------------+     +--------------------+     |    |
         |   | - idFuncionario: INT |   | - idProduto: INT   |     |    |
         |   | - matricula: VARCHAR |   | - nome: VARCHAR    |     |    |
         |   | - idPessoa: INT       |   | - preco: DECIMAL   |     |    |
         |   | - idArea: INT         |   | - categoria: ENUM  |     |    |
         |   | - salario: DECIMAL    |   | - descricao: TEXT  |     |    |
         |   +------------------+     +--------------------+     |    |
         |            ^                       |                   |    |
         |            |                       |                   |    |
         |            |                       +-------------------+    |
         |            |                                ^             |
         |            |                                |             |
         |            |                                |             |
         |            |                       +--------------------+ |
         |            |                       | clientes_produtos  | |
         |            |                       +--------------------+ |
         |            |                       | - idCliente: INT    | |
         |            |                       | - idProduto: INT    | |
         |            +-----------------------+--------------------+ |
         |                                +------------------------+ |
         +----------------------------------------------------------------
