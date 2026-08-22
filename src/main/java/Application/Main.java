package Application;

import Model.TipoTransacao;
import Service.SistemaFinanceiro;
import View.ConsoleView;

import java.util.Scanner;

/**
 * Main apenas inicializa a aplicação e executa o loop principal.
 * A responsabilidade de ler dados e montar a interface fica em ConsoleView.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemaFinanceiro sistemaFinanceiro = new SistemaFinanceiro();
        ConsoleView consoleView = new ConsoleView(sistemaFinanceiro, scanner);

        int opcao;

        do {
            opcao = consoleView.exibirMenu();

            switch (opcao) {
                case 1:
                    consoleView.adicionarTransacao(TipoTransacao.RECEITA);
                    break;
                case 2:
                    consoleView.adicionarTransacao(TipoTransacao.DESPESA);
                    break;
                case 3:
                    consoleView.atualizarTransacao();
                    break;
                case 4:
                    consoleView.listarTransacoes();
                    break;
                case 5:
                    consoleView.mostrarSaldo();
                    break;
                case 6:
                    consoleView.buscarPorCategoria();
                    break;
                case 7:
                    consoleView.buscarPorId();
                    break;
                case 8:
                    consoleView.buscarPorDescricao();
                    break;
                case 9:
                    consoleView.removerTransacao();
                    break;
                case 0:
                    System.out.println("Encerrando sistema...");
                    scanner.close();
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 0);
    }
}
