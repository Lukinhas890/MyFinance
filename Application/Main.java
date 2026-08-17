import java.util.Scanner;
import Model.Transacao;
import Model.Categoria;
import Model.TipoTransacao;
import Model.TransacaoNaoEncontradaException;
import Service.SistemaFinanceiro;


public class Main {
    public static void main(String[] args) throws TransacaoNaoEncontradaException {
        Scanner scanner = new Scanner(System.in);
        SistemaFinanceiro sistemaFinanceiro = new SistemaFinanceiro();
        int opcao;

            do{
                System.out.println("1 - Adicionar receita");
                System.out.println("2 - Adicionar despesa");
                System.out.println("3 - Atualizar despesa");
                System.out.println("4 - Listar transações");
                System.out.println("5 - Ver saldo atual");
                System.out.println("6 - Buscar por categoria");
                System.out.println("7 - Buscar por ID");
                System.out.println("8 - Buscar por descrição");
                System.out.println("9 - Remover transação");
                System.out.println("0 - Sair");
                System.out.println("Escolha uma opção:");
                opcao = scanner.nextInt();

                switch (opcao){
                    case 1:
                        adicionarTransacao(scanner, sistemaFinanceiro, TipoTransacao.RECEITA);
                        break;
                    case 2:
                        adicionarTransacao(scanner, sistemaFinanceiro, TipoTransacao.DESPESA);
                        break;
                    case 3:
                        atualizarTransacao(scanner, sistemaFinanceiro);
                        break;
                    case 4:
                        sistemaFinanceiro.listarTransacoes();
                        break;
                    case 5:
                        double saldo = sistemaFinanceiro.calcularSaldo();
                        System.out.println("Seu saldo é de: " + saldo);
                        break;
                    case 6:
                        buscarPorCategoria(scanner, sistemaFinanceiro);
                        break;
                    case 7:
                        buscarPorId(scanner, sistemaFinanceiro);
                        break;
                    case 8:
                        buscarPorDescricao(scanner, sistemaFinanceiro);
                        break;
                    case 9:
                        removerTransacao(scanner, sistemaFinanceiro);
                        break;
                    case 0:
                        scanner.close();
                        break;
                }
            } while (opcao != 0);

        }

        public static void adicionarTransacao(Scanner scanner, SistemaFinanceiro sistemaFinanceiro, TipoTransacao tipo){
            double valor;
            Categoria categoria;
            String descricao;
            String entradaUsuario;
            String textoFormatado;
            if(tipo == TipoTransacao.RECEITA){
                System.out.println("Digite o valor da receita: ");
                valor = scanner.nextDouble();
                scanner.nextLine();
            }
            else{
                System.out.println("Digite o valor da despesa: ");
                valor = scanner.nextDouble();
                scanner.nextLine();
            }
            System.out.println("Digite a categoria (ALIMENTACAO, TRANSPORTE, LAZER, ESTUDOS):");
            entradaUsuario = scanner.nextLine();

            System.out.println("Digite a descrição");
            descricao = scanner.nextLine();

            try {
                // Tratamento: remove espaços e joga tudo para MAIÚSCULAS
                textoFormatado = entradaUsuario.trim().toUpperCase();

                // Converte a String para o Enum correspondente
                categoria = Categoria.valueOf(textoFormatado);

                sistemaFinanceiro.adicionarTransacao(valor, categoria, tipo, descricao);

            } catch (IllegalArgumentException e) {
                    // Esse erro acontece se o usuário digitar algo que não está no Enum (ex: "FESTA")
                    System.out.println("Erro: A categoria '" + entradaUsuario + "' não existe no sistema.");
            }
        }

        public static void atualizarTransacao(Scanner scanner, SistemaFinanceiro sistemaFinanceiro) {

    System.out.println("Digite o ID da transação que deseja atualizar:");
    int id = scanner.nextInt();
    scanner.nextLine();

    System.out.println("Digite o novo valor:");
    double valor = scanner.nextDouble();
    scanner.nextLine();

    System.out.println(
        "Digite a nova categoria " +
        "(ALIMENTACAO, TRANSPORTE, LAZER, ESTUDOS):"
    );

    String entradaUsuario = scanner.nextLine();

    Categoria categoria;

    try {
        categoria = Categoria.valueOf(
            entradaUsuario.trim().toUpperCase()
        );
    } catch (IllegalArgumentException e) {
        System.out.println("Categoria inválida.");
        return;
    }

    System.out.println("Digite o novo tipo (RECEITA ou DESPESA):");
    TipoTransacao tipo;

    try {
        tipo = TipoTransacao.valueOf(
            scanner.nextLine().trim().toUpperCase()
        );
    } catch (IllegalArgumentException e) {
        System.out.println("Tipo de transação inválido.");
        return;
    }

    System.out.println("Digite a nova descrição:");
    String descricao = scanner.nextLine();

    try {

        sistemaFinanceiro.atualizarTransacao(
            id,
            valor,
            categoria,
            tipo,
            descricao
        );

    } catch (TransacaoNaoEncontradaException e) {
        System.out.println(e.getMessage());
    }
}

        public static void buscarPorCategoria(Scanner scanner, SistemaFinanceiro sistemaFinanceiro){
            Categoria categoria;
            String entradaUsuario;
            String textoFormatado;
            System.out.println("Digite a categoria (ALIMENTACAO, TRANSPORTE, LAZER, ESTUDOS):");
            scanner.nextLine();
            entradaUsuario = scanner.nextLine();
            try{
                textoFormatado = entradaUsuario.trim().toUpperCase();
                categoria = Categoria.valueOf(textoFormatado);
                sistemaFinanceiro.buscarPorCategoria(categoria);
            } catch (TransacaoNaoEncontradaException e) {
                // Esse erro acontece se o usuário digitar algo que não está no Enum (ex: "FESTA")
                System.out.println("Erro: A categoria '" + entradaUsuario + "' não existe no sistema.");                          
            }
        }

        public static void buscarPorId(Scanner scanner, SistemaFinanceiro sistemaFinanceiro){
            int entradaUsuario;
            System.out.println("Digite o Id:");
            scanner.nextInt();
            entradaUsuario = scanner.nextInt();
            try{
                sistemaFinanceiro.buscarPorId(entradaUsuario);
            } catch (TransacaoNaoEncontradaException e) {
                // Esse erro acontece se o usuário digitar algo que não está no Enum (ex: "FESTA")
                System.out.println("Erro: O ID '" + entradaUsuario + "' não existe no sistema.");                          
            }
        }

        public static void buscarPorDescricao(Scanner scanner, SistemaFinanceiro sistemaFinanceiro) throws TransacaoNaoEncontradaException{
            String entradaUsuario;
            String textoFormatado;
            System.out.println("Digite a descrição:");
            scanner.nextLine();
            entradaUsuario = scanner.nextLine();
            try{
                textoFormatado = entradaUsuario.trim().toUpperCase();
                sistemaFinanceiro.buscarPorDescricao(textoFormatado);
            } catch (IllegalArgumentException e) {
                // Esse erro acontece se o usuário digitar algo que não está no Enum (ex: "FESTA")
                System.out.println("Erro: A descrição '" + entradaUsuario + "' não existe no sistema.");                          
            }
        }

        public static void removerTransacao(Scanner scanner, SistemaFinanceiro sistemaFinanceiro){
            int id;
            System.out.println("Digite o ID da transação: "); 
            id = scanner.nextInt();
            scanner.nextLine();

            try{
                Transacao removida = sistemaFinanceiro.removerTransacao(id);
                System.out.println("Transação removida com sucesso");
                System.out.println(removida);

            } catch (IllegalArgumentException e) {
                // Esse erro acontece se o usuário digitar algo que não está no Enum (ex: "FESTA")
                System.out.println("Erro: O ID '" + id + "' não existe no sistema.");
            }
        }
    }