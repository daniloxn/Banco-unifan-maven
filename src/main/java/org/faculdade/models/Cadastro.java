package org.faculdade.models;


import org.faculdade.app.Banco;

import br.com.caelum.stella.validation.CPFValidator;

public class Cadastro {

    public static Conta criarConta() {
        String nome;
        do {
            System.out.println("\n╔══════════════════════════════════╗");
            System.out.println("║           NOVO CADASTRO          ║");
            System.out.println("╠══════════════════════════════════╣");
            System.out.print("║ ➤ Nome: ");
            nome = Banco.input.nextLine();
            if (!nome.matches("[A-Za-zÀ-ÖØ-öø-ÿ\\s]+")) {
                System.out.println("║ ➤ Nome inválido!");
                System.out.println("╚══════════════════════════════════╝");

                nome = null; // força repetir
            }
        } while (nome == null);

        String cpf;
        do {
            System.out.println("╠══════════════════════════════════╣");
            System.out.print("║ ➤ CPF (11 dígitos): ");
            cpf = Banco.input.next();

            if (!validaCpf(cpf)) {
                System.out.println("║ ➤ CPF inválido, digite novamente.");
                cpf = null; // força repetir
            } 
        } while (cpf == null);

        Banco.input.nextLine();

        String email;
        do {
            System.out.println("╠══════════════════════════════════╣");
            System.out.print("║ ➤ Email: ");
            email = Banco.input.next();
            if (!email.contains("@") || !email.contains(".")) {
                System.out.println("║ ➤ Email inválido, digite novamente. ");
            }
        } while (!email.contains("@") || !email.contains("."));

        String senha;
        do {
            System.out.println("╠══════════════════════════════════╣");
            System.out.print("║ ➤ Senha (somente números): ");
            senha = Banco.input.next();

            if (!senha.matches("\\d+")) { // verifica se tem apenas dígitos
                System.out.println("║ ➤ Senha inválida! Digite apenas números.");
                senha = null; // força repetir
            }
        } while (senha == null);

        System.out.println("╚══════════════════════════════════════╝");

        Banco.credenciaisDeAcesso.put(cpf, senha);
        Cliente pessoa = new Cliente(nome, cpf, email);

        // CONTA TESTE


        int tipo = 0;
        do {
            System.out.println("\n╔══════════════════════════════════╗");
            System.out.println("║     ESCOLHA O TIPO DA CONTA      ║");
            System.out.println("╠══════════════════════════════════╣");
            System.out.println("║ 1 - Conta Corrente               ║");
            System.out.println("║ 2 - Conta Poupança               ║");
            System.out.println("╠══════════════════════════════════╣");
            tipo = Banco.lerInteiro("║ ➤ Opção: ");


            if (tipo != 1 && tipo != 2) {
                System.out.println("║ ➤ Opção inválida! Digite 1 ou 2.");
                tipo = 0;
            }
        } while (tipo == 0);
        System.out.println("╚══════════════════════════════════╝");


        Conta conta;
        String cTipo;
        if (tipo == 1) {
            conta = new ContaCorrente(pessoa);
            cTipo = "Corrente";
        } else {
            conta = new ContaPoupanca(pessoa);
            cTipo = "Poupanca";
        }
        Banco.contasBancarias.add(conta);
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println(" Conta " +cTipo+ " foi criada com sucesso!");
        System.out.println("╚══════════════════════════════════════╝");
        Banco.intervalo();


        for (Conta c: Banco.contasBancarias){
            if (c.getCliente().getCpf().equals(cpf)) {
                Banco.contaLogada = c;
                break;
            }
        }
        return conta;
    }

    public static boolean validaCpf(String cpf) {
        CPFValidator cpfValidator = new CPFValidator();
        try {
            cpfValidator.assertValid(cpf);
            return true;
        } catch(Exception e){
            return false;
        }
    }
}
