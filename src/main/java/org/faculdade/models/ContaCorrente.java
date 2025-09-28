package org.faculdade.models;



public class ContaCorrente extends Conta {
    public ContaCorrente(Cliente cliente) {
        super(cliente);
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

    public void depositar(Double valor) {
        super.depositar(valor); // mantém o depósito normal
        aplicarTarifaMensal();  // aplica a tarifa logo depois
    }

    @Override
    public String getTipoConta() {
        return "Conta Corrente";
    }
}

