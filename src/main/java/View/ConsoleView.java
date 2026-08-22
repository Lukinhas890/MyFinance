package View;

import Model.Categoria;
import Model.TipoTransacao;
import Model.Transacao;
import Model.TransacaoNaoEncontradaException;
import Service.SistemaFinanceiro;
import Util.ConsoleInput;

import java.util.List;
import java.util.Scanner;

/**
 * Camada de apresentação: apenas coleta entradas do usuário, valida o que for necessário
 * e repassa objetos prontos para a camada de serviço.
 */
public class ConsoleView {

    private final ConsoleInput input;
    private final SistemaFinanceiro sistemaFinanceiro;

    public ConsoleView(SistemaFinanceiro sistemaFinanceiro, Scanner scanner) {
        this.sistemaFinanceiro = sistemaFinanceiro;
        this.input = new ConsoleInput(scanner);
    }

    public int exibirMenu() {
        System.out.println();
        System.out.println("1 - Adicionar receita");
        System.out.println("2 - Adicionar despesa");
        System.out.println("3 - Atualizar transação");
        System.out.println("4 - Listar transações");
        System.out.println("5 - Ver saldo atual");
        System.out.println("6 - Buscar por categoria");
        System.out.println("7 - Buscar por ID");
        System.out.println("8 - Buscar por descrição");
        System.out.println("9 - Remover transação");
        System.out.println("0 - Sair");

        return input.readInt("Escolha uma opção: ");
    }

    public void adicionarTransacao(TipoTransacao tipo) {
        double valor = input.readNonNegativeDouble(
                tipo == TipoTransacao.RECEITA ? "Digite o valor da receita: " : "Digite o valor da despesa: "
        );

        Categoria categoria = input.readCategoria("Digite a categoria (ALIMENTACAO, TRANSPORTE, LAZER, ESTUDOS): ");
        String descricao = input.readDescricao("Digite a descrição: ");

        sistemaFinanceiro.adicionarTransacao(valor, categoria, tipo, descricao);
        System.out.println("Transação adicionada com sucesso.");
    }

    public void atualizarTransacao() {
        int id = input.readPositiveInt("Digite o ID da transação que deseja atualizar: ");
        double valor = input.readNonNegativeDouble("Digite o novo valor: ");
        Categoria categoria = input.readCategoria("Digite a nova categoria: ");
        TipoTransacao tipo = input.readTipoTransacao("Digite o novo tipo (RECEITA ou DESPESA): ");
        String descricao = input.readDescricao("Digite a nova descrição: ");

        try {
            sistemaFinanceiro.atualizarTransacao(id, valor, categoria, tipo, descricao);
            System.out.println("Transação atualizada com sucesso.");
        } catch (TransacaoNaoEncontradaException e) {
            System.out.println(e.getMessage());
        }
    }

    public void listarTransacoes() {
        sistemaFinanceiro.listarTransacoes();
    }

    public void mostrarSaldo() {
        double saldo = sistemaFinanceiro.calcularSaldo();
        System.out.println("Seu saldo é de: " + saldo);
    }

    public void buscarPorCategoria() {
        Categoria categoria = input.readCategoria("Digite a categoria para consultar: ");

        try {
            List<Transacao> transacoes = sistemaFinanceiro.buscarPorCategoria(categoria);
            if (transacoes.isEmpty()) {
                System.out.println("Nenhuma transação encontrada para a categoria: " + categoria);
                return;
            }

            for (Transacao transacao : transacoes) {
                System.out.println(transacao);
            }
        } catch (TransacaoNaoEncontradaException e) {
            System.out.println(e.getMessage());
        }
    }

    public void buscarPorId() {
        int id = input.readPositiveInt("Digite o ID da transação: ");

        try {
            Transacao transacao = sistemaFinanceiro.buscarPorId(id);
            System.out.println(transacao);
        } catch (TransacaoNaoEncontradaException e) {
            System.out.println(e.getMessage());
        }
    }

    public void buscarPorDescricao() {
        String descricao = input.readDescricao("Digite a descrição para buscar: ");

        try {
            List<Transacao> transacoes = sistemaFinanceiro.buscarPorDescricao(descricao);
            if (transacoes.isEmpty()) {
                System.out.println("Nenhuma transação encontrada para: " + descricao);
                return;
            }

            for (Transacao transacao : transacoes) {
                System.out.println(transacao);
            }
        } catch (TransacaoNaoEncontradaException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removerTransacao() {
        int id = input.readPositiveInt("Digite o ID da transação que deseja remover: ");

        Transacao removida = sistemaFinanceiro.removerTransacao(id);

        if (removida == null) {
            System.out.println("Nenhuma transação encontrada com o ID: " + id);
            return;
        }

        System.out.println("Transação removida com sucesso.");
        System.out.println(removida);
    }
}
