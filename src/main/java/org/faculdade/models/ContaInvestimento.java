package org.faculdade.models;



import java.util.Random;
import java.util.Scanner;

public class ContaInvestimento extends Conta {

    private static Scanner input = new Scanner(System.in);
    private Random random = new Random();

    public ContaInvestimento(Cliente cliente) {
        super(cliente);
    }

    @Override
    public String getTipoConta() {
        return "Investimento";
    }

    // ===================== MÉTODOS DE LEITURA =====================
    public static int lerInteiro(String prompt) {
        int numero = 0;
        while (true) {
            System.out.print(prompt);
            String entrada = input.nextLine();
            if (entrada.matches("\\d+")) {
                numero = Integer.parseInt(entrada);
                break;
            } else {
                System.out.println("➤ Entrada inválida! Digite apenas números inteiros.");
            }
        }
        return numero;
    }

    public static double lerDouble(String prompt) {
        double numero = 0;
        while (true) {
            System.out.print(prompt);
            String entrada = input.nextLine();
            if (entrada.matches("\\d+(\\.\\d+)?")) {
                numero = Double.parseDouble(entrada);
                if (numero > 0) break;
                else System.out.println("➤ Valor inválido! Digite um número maior que zero.");
            } else {
                System.out.println("➤ Entrada inválida! Digite apenas números (use ponto para decimais).");
            }
        }
        return numero;
    }

    // ===================== MENU INVESTIMENTOS =====================
    public void menuInvestimentos() {
        int opcao = 0;
        do {
            System.out.println("\n╔══════════════════════════════╗");
            System.out.println("║       MENU INVESTIMENTOS     ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("║ 1 - Criptomoedas             ║");
            System.out.println("║ 2 - Ações                    ║");
            System.out.println("║ 3 - Fundos Imobiliários      ║");
            System.out.println("║ 4 - Voltar                   ║");
            System.out.println("╚══════════════════════════════╝");

            opcao = lerInteiro("➤ Escolha uma opção: ");
            if (opcao < 1 || opcao > 4) {
                System.out.println("➤ Opção inválida! Digite de 1 a 4.");
                opcao = 0;
            }

        } while (opcao == 0);

        switch (opcao) {
            case 1 -> investirCriptomoedas();
            case 2 -> investirAcoes();
            case 3 -> investirFundosImobiliarios();
            case 4 -> { return; }
        }
    }

    // ===================== INVESTIMENTOS =====================
    private void investirCriptomoedas() {
        int opcao = 0;
        do {
            System.out.println("\n╔══════════════════════════════╗");
            System.out.println("║     INVESTIR EM CRIPTO       ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("║ 1 - Bitcoin (BTC)            ║");
            System.out.println("║ 2 - Ethereum (ETH)           ║");
            System.out.println("║ 3 - Solana (SOL)             ║");
            System.out.println("║ 4 - Dogecoin (DOGE)          ║");
            System.out.println("╚══════════════════════════════╝");

            opcao = lerInteiro("➤ Escolha uma opção: ");
            if (opcao < 1 || opcao > 4) {
                System.out.println("➤ Opção inválida! Digite de 1 a 4.");
                opcao = 0;
            }
        } while (opcao == 0);

        String moeda = switch (opcao) {
            case 1 -> "Bitcoin";
            case 2 -> "Ethereum";
            case 3 -> "Solana";
            case 4 -> "Dogecoin";
            default -> "";
        };

        double valorInvestimento = lerDouble("Digite o valor que deseja investir: ");

        if (valorInvestimento > this.getSaldo()) {
            System.out.println("➤ Saldo insuficiente! Você tem apenas R$ " + this.getSaldo());
            return;
        }

        double resultado = valorPercentual(-0.5, 1.5, valorInvestimento);
        investirRisco(resultado, valorInvestimento, "Criptomoeda: " + moeda);
    }

    private void investirAcoes() {
        double valorInvestimento = lerDouble("Informe o valor que deseja investir em Ações: ");

        if (valorInvestimento > this.getSaldo()) {
            System.out.println("➤ Saldo insuficiente! Você tem apenas R$ " + this.getSaldo());
            return;
        }

        double resultado = valorPercentual(-0.3, 1.0, valorInvestimento);
        investirRisco(resultado, valorInvestimento, "Ações");
    }

    private void investirFundosImobiliarios() {
        double valorInvestimento = lerDouble("Informe o valor que deseja investir em Fundos Imobiliários: ");

        if (valorInvestimento > this.getSaldo()) {
            System.out.println("➤ Saldo insuficiente! Você tem apenas R$ " + this.getSaldo());
            return;
        }

        double resultado = valorPercentual(-0.1, 0.5, valorInvestimento);
        investirRisco(resultado, valorInvestimento, "Fundos Imobiliários");
    }

    // ===================== MÉTODOS DE APOIO =====================
    private void investirRisco(double resultado, double valorInvestido, String tipo) {
        double ganhoOuPerda = resultado - (this.getSaldo() - valorInvestido);

        if (ganhoOuPerda > 0) {
            System.out.printf("Lucro obtido em %s: R$ %.2f%n", tipo, ganhoOuPerda);
        } else if (ganhoOuPerda < 0) {
            System.out.printf("Perda ocorrida em %s: R$ %.2f%n", tipo, Math.abs(ganhoOuPerda));
        } else {
            System.out.printf("Nenhum ganho em %s.%n", tipo);
        }

        this.setSaldo(resultado);
        System.out.printf("Novo saldo: R$ %.2f%n", this.getSaldo());
    }

    // Calcula valor final baseado em risco percentual
    private double valorPercentual(double min, double max, double valorInvestido) {
        double porcentagem = min + (max - min) * random.nextDouble();
        return this.getSaldo() - valorInvestido + valorInvestido + (valorInvestido * porcentagem);
    }

    @Override
    public boolean sacar() {
        throw new UnsupportedOperationException("Unimplemented method 'sacar'");
    }
}

