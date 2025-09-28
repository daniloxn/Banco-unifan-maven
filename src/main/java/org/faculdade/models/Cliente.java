package org.faculdade.models;


public class Cliente {
    private String nome;
    private String cpf;
    private String email;

    public Cliente(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String cpfFormatado() {
        return String.format("%s.%s.%s-%s", this.cpf.substring(0, 3), this.cpf.substring(3, 6), this.cpf.substring(6, 9), this.cpf.substring(9, 11));
    }
    @Override
    public String toString() {
        return "[Nome=" + nome + ", CPF=" + cpf + ", Email=" + email + "]";
    }
}

