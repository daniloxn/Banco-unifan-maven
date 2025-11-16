# 🏦 Banco UNIFAN – Sistema Bancário em Java

Este projeto foi desenvolvido como trabalho acadêmico para a disciplina de Programação Orientada a Objetos.
O objetivo foi criar um sistema bancário básico, utilizando conceitos fundamentais de POO, como herança, polimorfismo, encapsulamento e abstração, além de boas práticas de organização de código em Java.

## 📌 Funcionalidades

- O sistema permite ao usuário:

- Criar uma conta bancária

- Fazer login

- Realizar depósitos

- Realizar saques

- Transferir valores entre contas

- Consultar saldo

- Suporta diferentes tipos de conta:

  - Conta Corrente

  - Conta Poupança

  - Conta Investimento

Cada tipo de conta possui comportamentos específicos, demonstrando o uso de polimorfismo.

## 📂 Estrutura do Projeto

A organização do projeto segue o padrão do Maven e está dividida da seguinte forma:
``` 
src/
└── main/
    └── java/
        └── org/
            └── faculdade/
                └── app/
                    ├── Banco.java
                    └── models/
                        ├── Cadastro.java
                        ├── Cliente.java
                        ├── Conta.java
                        ├── ContaCorrente.java
                        ├── ContaInvestimento.java
                        ├── ContaPoupanca.java
                        └── Login.java
```


## 🧩 Principais Classes

- Banco.java — Ponto de entrada e controle geral do sistema.

- Cliente.java — Armazena dados do usuário.

- Conta.java — Classe abstrata base para os tipos de conta.

- ContaCorrente / ContaPoupanca / ContaInvestimento — Implementações específicas que sobrescrevem métodos (polimorfismo).

- Cadastro.java — Responsável pela criação de contas.

- Login.java — Sistema básico de autenticação.

## 🛠️ Tecnologias Utilizadas

- Java (POO)

- Maven

- VS Code

- (Planejado) SQLite para persistência de dados

## 💡 Futuras Melhorias

Este projeto ainda está em desenvolvimento. Algumas melhorias planejadas:

- Implementar banco de dados SQLite

- Persistência de clientes

- Histórico de transações

- Segurança no armazenamento de dados

- Criar interface gráfica (Swing ou JavaFX)

- Implementar exceções mais completas

- Criar testes automatizados (JUnit)

## ▶️ Como Executar o Projeto

1️⃣. Certifique-se de ter o Java 17+ instalado.

2️⃣. Clone o repositório:

`git clone https://github.com/seu-repositorio/banco-unifan.git`

3️⃣. Entre no diretório:

`cd banco-unifan`

4️⃣. Compile com Maven:

`mvn compile`

5️⃣. Execute o programa:

`mvn exec:java`

# 📚 Conceitos de POO Utilizados

- ✔ Encapsulamento
- ✔ Herança
- ✔ Polimorfismo
- ✔ Classes e Objetos
- ✔ Abstração
- ✔ Organização em pacote

👨‍💻 Autor

Danilo Augusto dos Santos

Estudante de Engenharia de software – UNIFAN




