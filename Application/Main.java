import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemaFinanceiro sistemaFinanceiro = new SistemaFinanceiro();
        int opcao;
            do{
                System.out.println("1 - Adicionar receita");
                System.out.println("2 - Adicionar despesa");
                System.out.println("3 - Listar transações");
                System.out.println("4 - Ver saldo atual");
                System.out.println("5 - Buscar por categoria");
                System.out.println("6 - Remover transação");
                System.out.println("7 - Sair");
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
                                sistemaFinanceiro.listarTransacoes();
                                break;
                                case 4:
                                    double saldo = sistemaFinanceiro.calcularSaldo();
                                    System.out.println("Seu saldo é de: " + saldo);
                                    break;
                                    case 5:
                                        buscarPorCategoria(scanner, sistemaFinanceiro);
                                        break;
                                        case 6:
                                            removerTransacao(scanner, sistemaFinanceiro);
                                            break;
                                            case 7:
                                                scanner.close();
                                                break;
                }
            } while (opcao != 7);


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
            } catch (IllegalArgumentException e) {
                // Esse erro acontece se o usuário digitar algo que não está no Enum (ex: "FESTA")
                System.out.println("Erro: A categoria '" + entradaUsuario + "' não existe no sistema.");                          
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