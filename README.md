# MyFinance

Sistema financeiro desenvolvido em Java para gerenciamento de receitas e despesas.

O projeto começou como uma aplicação Java utilizando armazenamento em memória e evoluiu para utilizar **MySQL** como banco de dados, com acesso realizado através de **JDBC** e organização da persistência utilizando o padrão **DAO (Data Access Object)**.

## Funcionalidades

* Adicionar receitas
* Adicionar despesas
* Listar transações
* Calcular saldo
* Buscar transação por ID
* Buscar transações por categoria
* Buscar transações por descrição
* Atualizar transações
* Remover transações
* Persistência dos dados em banco de dados MySQL

## Tecnologias utilizadas

* Java
* MySQL
* JDBC
* Git / GitHub
* Visual Studio Code
* MySQL Workbench

## Estrutura do projeto

```text
MyFinance/
│
├── Application/
│   └── Main.java
│
├── DAO/
│   └── TransacaoDAO.java
│
├── Model/
│   ├── Categoria.java
│   ├── TipoTransacao.java
│   ├── Transacao.java
│   └── TransacaoNaoEncontradaException.java
│
├── Service/
│   └── SistemaFinanceiro.java
│
└── Util/
    └── ConnectionFactory.java
```

## Banco de dados

O sistema utiliza um banco de dados MySQL para armazenar as transações.

A tabela `transacao` possui informações como:

* `id`
* `categoria`
* `valor`
* `tipo`
* `descricao`

## Arquitetura

O projeto utiliza uma separação básica de responsabilidades:

```text
Main
 ↓
SistemaFinanceiro
 ↓
TransacaoDAO
 ↓
MySQL
```

O `Main` é responsável pela interação com o usuário, o `SistemaFinanceiro` concentra as regras da aplicação e o `TransacaoDAO` é responsável pela comunicação com o banco de dados.

## Objetivo

Este projeto está sendo desenvolvido como forma de prática dos conhecimentos adquiridos em Java, com foco em **Programação Orientada a Objetos, JDBC, SQL, tratamento de exceções e persistência de dados**.

O projeto continuará sendo evoluído conforme novos conhecimentos forem adquiridos.
