package org.faculdade.models;


import org.faculdade.app.Banco;

public class Login {

    public static boolean fazerLogin() {
        boolean loginValido;
        String cpf;
        String senha;

        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║            LOGIN             ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.print("║ ➤ CPF: ");
        cpf = Banco.input.nextLine();

        System.out.print("║ ➤ Senha: ");
        senha = Banco.input.nextLine();
        System.out.println("╚══════════════════════════════╝");

        loginValido = Banco.credenciaisDeAcesso.containsKey(cpf) && Banco.credenciaisDeAcesso.get(cpf).equals(senha);

        if (!loginValido) {
            System.out.println("\nUsuário ou senha inválidos. Tente novamente.");
            return false;
        } else {
            for (Conta conta: Banco.contasBancarias){
                if (conta.getCliente().getCpf().equals(cpf)) {
                    Banco.contaLogada = conta;
                    break;
                }
            }
            System.out.println("\nLogin bem-sucedido!");
        }
        return true;
    }
}

