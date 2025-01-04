CREATE DATABASE marisa;
USE marisa;


CREATE TABLE pessoa (
    idPessoa INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    telefone VARCHAR(15) NOT NULL
);


CREATE TABLE clientes (
  idCliente INT PRIMARY KEY AUTO_INCREMENT,
  email VARCHAR(255) NOT NULL,
  idPessoa INT NOT NULL,
  FOREIGN KEY (idPessoa) REFERENCES pessoa(idPessoa)
);


CREATE TABLE areas (
   idArea INT PRIMARY KEY AUTO_INCREMENT,
   nome VARCHAR(255) NOT NULL
);


CREATE TABLE funcionarios (
    idFuncionario INT PRIMARY KEY AUTO_INCREMENT,
    matricula VARCHAR(20) UNIQUE NOT NULL,
    idPessoa INT NOT NULL,
    idArea INT NOT NULL,
    salario DECIMAL(10, 2),
    FOREIGN KEY (idArea) REFERENCES areas(idArea),
    FOREIGN KEY (idPessoa) REFERENCES pessoa(idPessoa)
);


CREATE TABLE produtos (
  idProduto INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(255) NOT NULL,
  preco DECIMAL(10, 2) NOT NULL,
  categoria ENUM('Eletrônicos', 'Móveis', 'Decoração', 'Vestuário', 'Vídeo-Games', 'Brinquedos') NOT NULL,
  descricao TEXT
);


CREATE TABLE clientes_produtos (
   idCliente INT NOT NULL,
   idProduto INT NOT NULL,
   PRIMARY KEY (idCliente, idProduto),
   FOREIGN KEY (idCliente) REFERENCES clientes(idCliente),
   FOREIGN KEY (idProduto) REFERENCES produtos(idProduto)
);


INSERT INTO areas (nome) VALUES ('Eletrônicos');
INSERT INTO areas (nome) VALUES ('Móveis');
INSERT INTO areas (nome) VALUES ('Decoração');
INSERT INTO areas (nome) VALUES ('Vestuário');
INSERT INTO areas (nome) VALUES ('Vídeo-Games');
INSERT INTO areas (nome) VALUES ('Brinquedos');

INSERT INTO pessoa (nome, cpf, telefone) VALUES ('João Silva', '12345678909', '11987654321');
INSERT INTO pessoa (nome, cpf, telefone) VALUES ('Maria Oliveira', '98765432100', '11876543210');


INSERT INTO clientes (email, idPessoa) VALUES ('joao.silva@example.com', 1);
INSERT INTO clientes (email, idPessoa) VALUES ('maria.oliveira@example.com', 2);


INSERT INTO funcionarios (matricula, idPessoa, idArea, salario) VALUES ('MAT001', 1, 1, 3000.00); -- João na área Eletrônicos
INSERT INTO funcionarios (matricula, idPessoa, idArea, salario) VALUES ('MAT002', 2, 2, 4000.00); -- Maria na área Móveis


INSERT INTO produtos (nome, preco, categoria, descricao)
VALUES ('Televisor', 1500.00, 'Eletrônicos', 'Televisor 50 polegadas 4K');
INSERT INTO produtos (nome, preco, categoria, descricao)
VALUES ('Sofá', 1200.00, 'Móveis', 'Sofá de 3 lugares confortável');
INSERT INTO produtos (nome, preco, categoria, descricao)
VALUES ('Cadeira Gamer', 800.00, 'Vídeo-Games', 'Cadeira gamer ergonômica');
