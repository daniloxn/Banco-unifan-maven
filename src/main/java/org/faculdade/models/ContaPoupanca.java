package org.faculdade.models;

import org.faculdade.app.Banco;

public class ContaPoupanca extends Conta {
    public ContaPoupanca(Cliente cliente) {
        super(cliente);
    }

    public void aplicarRendimento() {
        double rendimento = getSaldo() * 0.01;
        setSaldo(getSaldo() + rendimento);
        System.out.println("Rendimento de R$ " + rendimento + " aplicado.");
    }
    public void depositar(Double valor) {
        super.depositar(); // mantém o depósito normal
        aplicarRendimento();  // aplica a tarifa logo depois
    }
    @Override
    public boolean sacar() {
        double valorSaque;
        System.out.println("\n╔═════════════════════════════════════════════╗");
        System.out.println("║                    SAQUE                    ║");
        System.out.println("╠═════════════════════════════════════════════╣");
        valorSaque = Banco.lerInteiro("║ ➤ Digite um valor para saque: R$");

        if (valorSaque <= 0) {
            System.out.println("║ ➤ Valor inválido! Digite um valor maior que zero.");
            return false;
        } else { 
            if (this.getSaldo() >= valorSaque){
                setSaldo(getSaldo() - valorSaque);
                registrarMovimento("║ Saque: R$ " + valorSaque);
                System.out.println("║ Saque de R$" + valorSaque + " realizado com sucesso!");
                
            } else {
            registrarMovimento("║ Tentativa de saque falhou (R$ " + valorSaque + ")");
            System.out.println("║ ➤ Saldo insuficiente para saque.");
            return false;
            }
        }
        System.out.println("╚═════════════════════════════════════════════╝");
        Banco.intervalo();
        return true;
    }
    
    @Override
    public String getTipoConta() {
        return "Conta Poupança";
    }
}

