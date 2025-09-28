package org.faculdade.models;


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
        super.depositar(valor); // mantém o depósito normal
        aplicarRendimento();  // aplica a tarifa logo depois
    }
    @Override
    public String getTipoConta() {
        return "Conta Poupança";
    }
}

