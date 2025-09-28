package org.faculdade.models;

import org.faculdade.app.Banco;

public class ContaCorrente extends Conta {
    private double limite = 1000;

    public ContaCorrente(Cliente cliente) {
        super(cliente);
    }
    
    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }
    
    public void aplicarTarifaMensal() {
        double tarifa = 10.0;
        if (getSaldo() >= tarifa) {
            setSaldo(getSaldo() - tarifa);
            System.out.println("Tarifa mensal de R$ " + tarifa + " debitada.");
        } else {
            System.out.println("Saldo insuficiente para tarifa mensal.");
        }
    }

    public void depositar() {
        super.depositar(); // mantém o depósito normal
        aplicarTarifaMensal();  // aplica a tarifa logo depois
    }

    @Override
    public boolean sacar() {
        double valorSaque;
        String resposta;

        System.out.println("\n╔═════════════════════════════════════════════╗");
        System.out.println("║                    SAQUE                    ║");
        System.out.println("╠═════════════════════════════════════════════╣");
        valorSaque = Banco.lerInteiro("║ ➤ Digite um valor para saque: R$");

        if (valorSaque <= 0) {
            System.out.println("║ ➤ Valor inválido! Digite um valor maior que zero.");
            return false;
        } else { 
            if (this.getSaldo() >= valorSaque){
                // saldo suficiente
                setSaldo(getSaldo() - valorSaque);
                registrarMovimento("║ Saque: R$ " + valorSaque);
                System.out.println("║ Saque de R$" + valorSaque + " realizado com sucesso!");
                
            } else {
                double falta = valorSaque - this.getSaldo();

                if (limite >= falta) {
                    System.out.print("║ Saldo insuficiente. Deseja usar seu limite (Sim/Nao)? ");
                    resposta = Banco.input.nextLine();

                    if (resposta.equalsIgnoreCase("sim")) {
                        // usa todo saldo disponível e o restante do limite
                        limite -= falta;
                        setSaldo(0.0);

                        registrarMovimento("║ Saque utilizando limite: R$ " + valorSaque);
                        System.out.println("║ Saque de R$" + valorSaque + " realizado com sucesso!");
                        System.out.println("║ ➤ R$" + falta + " foram debitados do limite.");
                        System.out.println("║ Limite restante: R$" + limite);
                    } else if (resposta.equalsIgnoreCase("nao") || resposta.equalsIgnoreCase("não")) {
                        registrarMovimento("║ Saque cancelado pelo cliente (R$ " + valorSaque + ")");
                        System.out.println("║ ➤ Saque cancelado.");
                        return false;
                    }
                } else {
                    registrarMovimento("║ Tentativa de saque falhou (R$ " + valorSaque + ")");
                    System.out.println("║ ➤ Saldo insuficiente e limite indisponível para saque.");
                    return false;
                }
            }
        }

        System.out.println("╚═════════════════════════════════════════════╝");
        Banco.intervalo();
        return true;
    }

    @Override
    public String getTipoConta() {
        return "Conta Corrente";
    }
}
