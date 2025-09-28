package org.faculdade.models;


import org.faculdade.app.Banco;


import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public abstract class Conta {

    private static int contadorDeContas = 1;

    private int numeroConta;
    private Cliente cliente;
    private Double saldo = 0.0;
    private List<String> extrato;

    private String numeroCartao;

    public Conta(Cliente cliente) {
        this.numeroConta = contadorDeContas;
        this.cliente = cliente;
        contadorDeContas += 1;
        this.extrato = new ArrayList<>();
        this.numeroCartao = this.gerarNumeroCartao();
        registrarMovimento("║ Conta criada com sucesso!");
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public abstract String getTipoConta();

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public String getNumeroCartao() { return this.numeroCartao; }

    public String toString(){

        return "\n╔══════════════════════════════════════╗" +
                "\n Número da conta: " + this.getNumeroConta() +
                "\n Nome do Cliente: " + this.cliente.getNome() +
                "\n CPf: " + this.cliente.cpfFormatado() +
                "\n Email: " + this.cliente.getEmail() +
                "\n Tipo da Conta: " + this.getTipoConta() +
                "\n Saldo: " + Utils.doubleToString(this.getSaldo()) +
                "\n Numero do Cartão: " + this.getNumeroCartao() +
                "\n╚══════════════════════════════════════╝" +
                "\n";

    }

    public void depositar() {
        double valorDeposito;
        System.out.println("\n╔═════════════════════════════════════════════════════╗");
        System.out.println("║                      DEPÓSITO                       ║");
        System.out.println("╠═════════════════════════════════════════════════════╣");
        
        
        valorDeposito = Banco.lerInteiro("║ ➤ Digite um valor para depósito: R$");

        if (valorDeposito <= 0) {
            System.out.println("║ ➤ Valor inválido! Digite um valor maior que zero.");
        } else {
            setSaldo(getSaldo() + valorDeposito);
            System.out.println("║ Seu depósito de R$" + valorDeposito + " foi realizado com sucesso!");
            registrarMovimento("║ Depósito: R$ " + valorDeposito);
        } 
        System.out.println("╚═════════════════════════════════════════════════════╝");
        Banco.intervalo();
    }


    public abstract boolean sacar();

    /*

    public void saca1r() {
        double valorSaque;
        System.out.println("\n╔═════════════════════════════════════════════╗");
        System.out.println("║                    SAQUE                    ║");
        System.out.println("╠═════════════════════════════════════════════╣");
        valorSaque = Banco.lerInteiro("║ ➤ Digite um valor para saque: R$");

        if (valorSaque <= 0) {
            System.out.println("║ ➤ Valor inválido! Digite um valor maior que zero.");
        } else { 
            if (this.getSaldo() >= valorSaque){
                setSaldo(getSaldo() - valorSaque);
                registrarMovimento("║ Saque: R$ " + valorSaque);
                System.out.println("║ Saque de R$" + valorSaque + " realizado com sucesso!");
            } else {
            registrarMovimento("║ Tentativa de saque falhou (R$ " + valorSaque + ")");
            System.out.println("║ ➤ Saldo insuficiente para saque.");
            }
        }
        System.out.println("╚═════════════════════════════════════════════╝");
        Banco.intervalo();
    }
     * 
     */

    public void transferir() {
        int numeroContaDestinatario;
        double valor;

        System.out.println("\n╔═══════════════════════════════════════════════════════╗");
        System.out.println("║                      TRANSFERÊNCIA                    ║");
        System.out.println("╠═══════════════════════════════════════════════════════╣");
        numeroContaDestinatario = Banco.lerInteiro("║ ➤ Informe o número da Conta do Destinatário: ");

        Conta contaDestinatário = Banco.encontrarConta(numeroContaDestinatario);

        if (numeroContaDestinatario == Banco.contaLogada.getNumeroConta()) {
            System.out.println("║ ➤ Você não pode fazer transferência para sua própria conta.");
        } else {
            if (contaDestinatário != null) {

                valor = Banco.lerInteiro("║ ➤ Informe o valor da transferência: ");

                if (valor <= 0) {
                    System.out.println("║ ➤ Valor inválido! Digite um valor maior que zero.");
                } else {
                    if (this.getSaldo() >= valor) {
                        setSaldo(getSaldo() - valor);
                        contaDestinatário.saldo = contaDestinatário.getSaldo() + valor;

                        registrarMovimento("║ Transferência enviada: R$ " + valor + " -> Conta " + contaDestinatário.getNumeroConta());

                        contaDestinatário.registrarMovimento("║ Transferência recebida: R$ " + valor + " <- Conta " + this.getNumeroConta());
                        System.out.println("║ ➤ Transferência realizada com sucesso!");
                    } else {
                        registrarMovimento("║ Falha na transferência de R$ " + valor);
                        System.out.println("║ ➤ Saldo Insuficiente. Não foi possível realizar a transferência");
                    }
                }
            } else {
                System.out.println("║ ➤ Conta para transferência não encontrada.");
            }
        }
        System.out.println("╚═══════════════════════════════════════════════╝");
        Banco.intervalo();
    }

    public void registrarMovimento(String descricao) {
        extrato.add(descricao);
    }

    public void imprimirExtrato() {
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║                EXTRATO DA CONTA " + getNumeroConta()+"                ║");
        System.out.println("╠══════════════════════════════════════════════════╣");
        for (String movimento : extrato) {
            System.out.println(movimento);
        }
        System.out.println("║ Saldo atual: " + Utils.doubleToString(this.getSaldo()));
        System.out.println("╚══════════════════════════════════════════════════╝");

        Banco.intervalo();
    }

    public String gerarNumeroCartao() {
        Random random = new Random();
        StringBuilder numeroCartao = new StringBuilder();

        for (int i = 0; i < 16; i++) {
            int digito = random.nextInt(10);
            numeroCartao.append(digito);

            if ((i + 1) % 4 == 0 && i < 15){
                numeroCartao.append("-");
            }

        }
        return numeroCartao.toString();
    }
    public static class Utils { // precisa ser static se estiver dentro da Conta

        static NumberFormat formatandoValores = new DecimalFormat("R$ #,##0.00"); // duas casas
        static {
            ((DecimalFormat) formatandoValores).setRoundingMode(RoundingMode.HALF_UP); // arredonda corretamente
        }

        public static String doubleToString(Double valor){
            return formatandoValores.format(valor);
        }
    }
}

