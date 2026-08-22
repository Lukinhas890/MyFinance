package Util;

import Model.Categoria;
import Model.TipoTransacao;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Responsável por encapsular a leitura do teclado e validar entradas do usuário.
 * Essa classe centraliza a leitura segura de texto e números, evitando erros comuns
 * de Scanner como nextInt() seguido de nextLine().
 */
public class ConsoleInput {

    private final Scanner scanner;

    public ConsoleInput(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public String readNonEmptyString(String prompt) {
        while (true) {
            String valor = readLine(prompt).trim();

            if (!valor.isEmpty()) {
                return valor;
            }

            System.out.println("Valor inválido. O campo não pode estar vazio.");
        }
    }

    public int readInt(String prompt) {
        while (true) {
            String valor = readLine(prompt).trim();

            try {
                return Integer.parseInt(valor);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
            }
        }
    }

    public int readPositiveInt(String prompt) {
        while (true) {
            int valor = readInt(prompt);

            if (valor > 0) {
                return valor;
            }

            System.out.println("O valor deve ser maior que zero.");
        }
    }

    public double readDouble(String prompt) {
        while (true) {
            String valor = readLine(prompt).trim();

            try {
                return Double.parseDouble(valor);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número válido.");
            }
        }
    }

    public double readNonNegativeDouble(String prompt) {
        while (true) {
            double valor = readDouble(prompt);

            if (valor >= 0) {
                return valor;
            }

            System.out.println("O valor não pode ser negativo.");
        }
    }

    public Categoria readCategoria(String prompt) {
        while (true) {
            String valor = readNonEmptyString(prompt).trim().toUpperCase();

            try {
                return Categoria.valueOf(valor);
            } catch (IllegalArgumentException e) {
                System.out.println("Categoria inválida. Opções válidas: ALIMENTACAO, TRANSPORTE, LAZER, ESTUDOS");
            }
        }
    }

    public TipoTransacao readTipoTransacao(String prompt) {
        while (true) {
            String valor = readNonEmptyString(prompt).trim().toUpperCase();

            try {
                return TipoTransacao.valueOf(valor);
            } catch (IllegalArgumentException e) {
                System.out.println("Tipo inválido. Use RECEITA ou DESPESA.");
            }
        }
    }

    public String readDescricao(String prompt) {
        String descricao = readNonEmptyString(prompt);

        if (descricao.length() < 2) {
            System.out.println("A descrição deve conter pelo menos 2 caracteres.");
            return readDescricao(prompt);
        }

        return descricao;
    }
}
