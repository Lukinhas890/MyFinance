# MyFinance

Sistema de gerenciamento financeiro pessoal desenvolvido em Java, via linha de comando (console). Permite registrar receitas e despesas, organizá-las por categoria e acompanhar o saldo atual.

## Funcionalidades

- ➕ Adicionar receita
- ➖ Adicionar despesa
- 📋 Listar todas as transações
- 💰 Consultar saldo atual
- 🔍 Buscar transações por categoria
- 🗑️ Remover transação por ID

## Categorias disponíveis

- Alimentação
- Transporte
- Lazer
- Estudos

## Estrutura do projeto

```
MyFinance/
├── Application/
│   └── Main.java              # Ponto de entrada e menu interativo
├── Model/
│   ├── Categoria.java          # Enum com as categorias de transação
│   ├── TipoTransacao.java      # Enum: RECEITA ou DESPESA
│   └── Transacao.java          # Classe que representa uma transação
├── Service/
│   └── SistemaFinanceiro.java  # Regras de negócio (adicionar, remover, listar, calcular saldo)
└── Util/
```

## Tecnologias utilizadas

- Java
- Coleções (ArrayList)
- Programação orientada a objetos (POO)

## Como executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/MyFinance.git
   ```
2. Compile o projeto:
   ```bash
   javac Application/Main.java Model/*.java Service/*.java -d out
   ```
3. Execute:
   ```bash
   java -cp out Main
   ```

Ao rodar, um menu interativo será exibido no console para adicionar transações, listar, consultar saldo e mais.

## Melhorias futuras

- [ ] Persistência de dados (salvar em arquivo ou banco de dados)
- [ ] Interface gráfica
- [ ] Relatórios por período
- [ ] Testes unitários

## Autor

Desenvolvido como projeto de estudos em Java.
