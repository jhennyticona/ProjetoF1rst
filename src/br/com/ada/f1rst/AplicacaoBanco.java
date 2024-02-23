package br.com.ada.f1rst;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AplicacaoBanco {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Conta> contas = new ArrayList<>();
    private static ArrayList<Cliente> clientes = new ArrayList<>();

    public static void main(String[] args) {
        exibirMenu1(scanner, clientes);
    }

    public static void exibirMenu1(Scanner scanner, ArrayList<Cliente> clientes) {
        int opcao;

        do {
            System.out.println(Cores.TEXT_PURPLE_BOLD
                    + "********************************************");
            System.out.println("******** Bem Vindo ao LuaBank **************");
            System.out.println("******** O melhor banco da Lua ! ***********");
            System.out.println("***** Porque da Terra e o Chama ************");
            System.out.println("*********** Menu Principal *****************");
            System.out.println("********************************************");
            System.out.println("1. Já sou Cliente");
            System.out.println("2. Cadastro de Cliente");
            System.out.println("3. Listar todos os Clientes");
            System.out.println("0. Sair");
            System.out.println("Digite uma opção: ");
            int opcao2 = scanner.nextInt();

            boolean sair = false;

            switch (opcao2) {
                case 1:
                    sair = !exibirMenu2(scanner, clientes);
                    sair = false;
                    break;
                case 2:
                    cadastrarCliente(scanner, clientes);
                    break;
                case 3:
                    listarTodosClientes(clientes);
                    break;
                case 0:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
            if (sair) {
                break;
            }
        } while (true);
    }

    public static boolean exibirMenu2(Scanner scanner, ArrayList<Cliente> clientes) {

        do {
            System.out.println(Cores.TEXT_PURPLE_BOLD
                    + "********************************************");
            System.out.println("********************************************");
            System.out.println("*************** Menu Cliente ***************");
            System.out.println("********************************************");
            System.out.println("1. Abrir Conta");
            System.out.println("2. Depositar");
            System.out.println("3. Sacar");
            System.out.println("4. Transferência");
            System.out.println("5. Investir");
            System.out.println("6. Consultar Saldo");
            System.out.println("7. Listar todas as contas");
            System.out.println("0. Voltar ao menu");
            System.out.print("Escolha a opção: ");
            int opcao2 = scanner.nextInt();

            switch (opcao2) {
                case 1:
                    criarConta(scanner, clientes);
                    break;
                case 2:
                    depositar(scanner, contas);
                    break;
                case 3:
                    sacar(scanner, contas);
                    break;
                case 4:
                    transferencia(scanner, contas);
                    break;
                case 5:
                    investir(scanner, contas);
                    break;
                case 6:
                    consultarSaldo(scanner);
                    break;
                case 7:
                    listarContasCliente(scanner, clientes);
                    break;
                case 0:
                    return true;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (true);
    }

    public static void cadastrarCliente(Scanner scanner, ArrayList<Cliente> clientes) {
        TipoDeCliente tipoDeCliente = null;
        System.out.println("********************************************");
        System.out.println("*********** Cadastro de Cliente ************");
        System.out.println("********************************************");
        System.out.println("Digite seu nome:");
        String nome = scanner.next();
        System.out.println("Digite sua idade:");
        int idade = scanner.nextInt();
        if (idade >= 18) {
            System.out.println("Digite umas das opções: ");
            System.out.println("1. Pessoa Física: ");
            System.out.println("2. Pessoa Jurídica");
            int tipo = scanner.nextInt();
            switch (tipo) {
                case 1:
                    tipoDeCliente = TipoDeCliente.PESSOAFISICA;
                    break;
                case 2:
                    tipoDeCliente = TipoDeCliente.PESSOAJURIDICA;
                    break;
                default:
                    System.out.println("Opção de cliente inválida!");
                    return;
            }
            Cliente cliente = new Cliente(nome, tipoDeCliente, idade);
            clientes.add(cliente);
            System.out.println("Cliente criado com sucesso, " +
                    "o número do cliente: " + cliente.getCodigo());
            System.out.println("");
        } else {
            System.out.println("Não é possível criar a conta!");
        }
    }

    public static void listarTodosClientes(ArrayList<Cliente> clientes) {
        String tipoPessoa = "";
        if (clientes.isEmpty()) {
            System.out.println("Lista Vazia!");
        } else {
            for (Cliente c : clientes) {
                if (c.getTipoDeCliente().equals(TipoDeCliente.PESSOAJURIDICA)) {
                    tipoPessoa = "Pessoa Jurídica";
                } else if (c.getTipoDeCliente().equals(TipoDeCliente.PESSOAFISICA)) {
                    tipoPessoa = "Pessoa Física";
                }
                System.out.println("********************************************");
                System.out.println("Tipo de Cliente: " + tipoPessoa);
                System.out.println("Codigo do Cliente: " + c.getCodigo());
                System.out.println("Nome do Cliente: " + c.getNome());
                System.out.println("Idade do Cliente: " + c.getIdade());

                System.out.println("********************************************");
            }
        }
    }


    private static void criarConta(Scanner scanner, ArrayList<Cliente> clientes) {
        System.out.println("Digite o código do Cliente: ");
        String codigo = scanner.next();
        String tipoPessoa = "";
        boolean clienteEncontrado = false;
        for (Cliente c : clientes) {
            if (c.getCodigo().equals(codigo)) {
                clienteEncontrado = true;
                TipoDeCliente tipoDeCliente = c.getTipoDeCliente();
                if (c.getTipoDeCliente().equals(TipoDeCliente.PESSOAJURIDICA)) {
                    tipoPessoa = "Pessoa Jurídica";
                } else if (c.getTipoDeCliente().equals(TipoDeCliente.PESSOAFISICA)) {
                    tipoPessoa = "Pessoa Física";
                }
                if (tipoDeCliente != null) {
                    System.out.println("********************************************");
                    System.out.println("Tipo de Cliente: " + tipoPessoa);
                    System.out.println("********************************************");
                    int opcaoTipoConta;
                    if (tipoDeCliente == TipoDeCliente.PESSOAFISICA) {
                        System.out.println("Escolha uma opção: ");
                        System.out.println("1 - Conta Poupança");
                        System.out.println("2 - Conta Investimento");
                        opcaoTipoConta = scanner.nextInt();
                        switch (opcaoTipoConta) {
                            case 1:
                                Conta contaPoupanca = new Conta(c, TipoDeConta.POUPANCA, tipoDeCliente);
                                c.adicionarConta(contaPoupanca);
                                contas.add(contaPoupanca);
                                System.out.println("Conta Poupança criada com sucesso! O código da conta: " + contaPoupanca.getNumeroDaConta());
                                System.out.println("");
                                break;
                            case 2:
                                Conta contaInvestimento = new Conta(c, TipoDeConta.INVESTIMENTO, tipoDeCliente);
                                c.adicionarConta(contaInvestimento);
                                contas.add(contaInvestimento);
                                System.out.println("Conta Investimento criada com sucesso!O código da conta: " + contaInvestimento.getNumeroDaConta());
                                break;
                            default:
                                System.out.println("Opção inválida!");
                                break;
                        }
                    } else if (tipoDeCliente == TipoDeCliente.PESSOAJURIDICA) {
                        System.out.println("1 - Conta Corrente");
                        System.out.println("2 - Conta Investimento");
                        opcaoTipoConta = scanner.nextInt();
                        switch (opcaoTipoConta) {
                            case 1:
                                Conta contaCorrente = new Conta(c, TipoDeConta.CORRENTE, tipoDeCliente);
                                c.adicionarConta(contaCorrente);
                                contas.add(contaCorrente);
                                System.out.println("Conta Corrente criada com sucesso! O código da conta: " + contaCorrente.getNumeroDaConta());
                                break;
                            case 2:
                                Conta contaInvestimento = new Conta(c, TipoDeConta.INVESTIMENTO, tipoDeCliente);
                                c.adicionarConta(contaInvestimento);
                                contas.add(contaInvestimento);
                                System.out.println("Conta Investimento criada com sucesso! O código da conta: " + contaInvestimento.getNumeroDaConta());
                                break;
                            default:
                                System.out.println("Opção inválida!");
                                break;
                        }
                    }
                } else {
                    System.out.println("Tipo de cliente inválido!");
                }
            }
        }
        if (!clienteEncontrado) {
            System.out.println("Cliente não encontrado!");
        }
    }

    public static void depositar(Scanner scanner, ArrayList<Conta> contas) {
        System.out.print("Digite o número da conta para depósito: ");
        String numeroConta = scanner.next();
        Conta conta = null;
        for (Conta c : contas) {
            if (c.getNumeroDaConta().equals(numeroConta)) {
                conta = c;
                break;
            }
        }
        if (conta == null) {
            System.out.println("Conta não encontrada.");
            return;
        }

        Cliente cliente = conta.getCliente();
        TipoDeConta tipoDeConta = conta.getTipoDeConta();
        if (cliente.getTipoDeCliente() == TipoDeCliente.PESSOAFISICA) {
            if (tipoDeConta == TipoDeConta.POUPANCA) {
                System.out.print("Digite o valor a ser depositado: ");
                BigDecimal valorDeposito = scanner.nextBigDecimal();
                if (valorDeposito.compareTo(BigDecimal.ZERO) <= 0) {
                    System.out.println("O valor deve ser positivo.");
                } else {
                    conta.depositar(valorDeposito);
                    System.out.println("Depósito realizado com sucesso!");
                    System.out.println("Novo saldo da conta: R$ " + conta.getSaldo());
                }

            } else {
                System.out.println("Clientes pessoa física só podem depositar em contas poupança.");
            }
        } else if (cliente.getTipoDeCliente() == TipoDeCliente.PESSOAJURIDICA) {
            if (tipoDeConta == TipoDeConta.CORRENTE) {
                System.out.print("Digite o valor a ser depositado: R$ ");
                BigDecimal valorDeposito = scanner.nextBigDecimal();
                if (valorDeposito.compareTo(BigDecimal.ZERO) <= 0) {
                    System.out.println("O valor deve ser positivo.");
                } else {
                    conta.depositar(valorDeposito);
                    System.out.println("Depósito realizado com sucesso!");
                    System.out.println("Novo saldo da conta: R$ " + conta.getSaldo());
                }
            } else {
                System.out.println("Clientes pessoa jurídica só podem depositar em contas corrente.");
            }
        }
    }

    public static void sacar(Scanner scanner, ArrayList<Conta> contas) {
        System.out.print("Digite o número da conta para sacar: ");
        String numeroContaSaque = scanner.next();
        Conta contaSaque = null;
        for (Conta c : contas) {
            if (c.getNumeroDaConta().equals(numeroContaSaque)) {
                contaSaque = c;
                break;
            }
        }
        if (contaSaque != null) {

            System.out.print("Digite o valor para sacar: R$ ");
            BigDecimal valorSaque = scanner.nextBigDecimal();
            BigDecimal saldoAtual = contaSaque.getSaldo();

            TipoDeCliente tipoDeCliente = contaSaque.getCliente().getTipoDeCliente();

            if (tipoDeCliente == TipoDeCliente.PESSOAJURIDICA) {
                BigDecimal taxa = valorSaque.multiply(new BigDecimal("0.005"));
                valorSaque = valorSaque.add(taxa);
                System.out.println("Taxa de saque aplicada (0.5%): " + taxa);
            }

            if (valorSaque.compareTo(BigDecimal.ZERO) <= 0) {
                System.out.println("O valor de saque deve ser positivo.");
            } else if (valorSaque.compareTo(saldoAtual) <= 0) {
                contaSaque.sacar(valorSaque);
                System.out.println("Saque realizado com sucesso!");
                System.out.println("Novo saldo da conta: R$ " + contaSaque.getSaldo());
            } else {
                System.out.println("Saldo insuficiente para realizar o saque!");
            }
        } else {
            System.out.println("Conta não encontrada!");
        }
    }

    public static void consultarSaldo(Scanner scanner) {
        System.out.print("Digite o número da conta: ");
        String numeroConta = scanner.next();

        Conta conta = null;
        for (Conta c : contas) {
            if (c.getNumeroDaConta().equals(numeroConta)) {
                conta = c;
                break;
            }
        }

        if (conta != null) {
            System.out.println("O saldo atual da conta " + numeroConta + " é: R$ " + conta.getSaldo());
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    public static void listarContasCliente(Scanner scanner, ArrayList<Cliente> clientes) {
        if (clientes.isEmpty()) {
            System.out.println("Não há clientes cadastrados.");
        } else {
            System.out.println("Digite o código do cliente: ");
            String codigoCliente = scanner.next();
            boolean clienteEncontrado = false;

            for (Cliente cliente : clientes) {
                if (cliente.getCodigo().equals(codigoCliente)) {
                    clienteEncontrado = true;
                    System.out.println("********************************************");
                    System.out.println("Contas do cliente: " + cliente.getNome());
                    System.out.println("Cliente do tipo: " + cliente.getTipoDeCliente());
                    List<Conta> contasCliente = cliente.getContas();

                    if (contasCliente.isEmpty()) {
                        System.out.println("O cliente não possui contas.");
                    } else {
                        for (Conta conta : contasCliente) {
                            System.out.println("********************************************");
                            System.out.println("Tipo de Conta: " + conta.getTipoDeConta());
                            System.out.println("Agência: " + conta.getAgencia());
                            System.out.println("Número da Conta: " + conta.getNumeroDaConta());
                            System.out.println("Saldo: R$ " + conta.getSaldo());
                            System.out.println("********************************************");
                        }
                    }
                }
            }

            if (!clienteEncontrado) {
                System.out.println("Cliente não encontrado.");
            }
        }
    }

    public static void transferencia(Scanner scanner, ArrayList<Conta> contas) {
        System.out.print("Digite o número da conta de origem: ");
        String numeroContaOrigem = scanner.next();
        Conta contaOrigem = null;
        for (Conta c : contas) {
            if (c.getNumeroDaConta().equals(numeroContaOrigem)) {
                contaOrigem = c;
                break;
            }
        }
        if (contaOrigem == null) {
            System.out.println("Conta de origem não encontrada.");
            return;
        }

        System.out.print("Digite o número da conta de destino: ");
        String numeroContaDestino = scanner.next();
        Conta contaDestino = null;
        for (Conta c : contas) {
            if (c.getNumeroDaConta().equals(numeroContaDestino)) {
                contaDestino = c;
                break;
            }
        }
        if (contaDestino == null) {
            System.out.println("Conta de destino não encontrada.");
            return;
        }

        System.out.print("Digite o valor a ser transferido: R$ ");
        BigDecimal valorTransferencia = scanner.nextBigDecimal();

        BigDecimal saldoOrigem = contaOrigem.getSaldo();
        if (valorTransferencia.compareTo(saldoOrigem) > 0) {
            System.out.println("O valor da transferência é maior que o saldo da conta de origem.");
            return;
        }
        if (valorTransferencia.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("O valor da transferência deve ser positivo.");
        } else if (valorTransferencia.compareTo(saldoOrigem) <= 0) {
            TipoDeCliente tipoDeCliente = contaOrigem.getCliente().getTipoDeCliente();
            if (tipoDeCliente == TipoDeCliente.PESSOAJURIDICA) {
                BigDecimal taxa = valorTransferencia.multiply(new BigDecimal("0.005"));
                BigDecimal valorTransferidoComTaxa = valorTransferencia.add(taxa);
                if (saldoOrigem.compareTo(valorTransferidoComTaxa) >= 0) {
                    contaOrigem.sacar(valorTransferidoComTaxa);
                    contaDestino.depositar(valorTransferencia);
                    System.out.println("Transferência realizada com sucesso!");
                    System.out.println("Taxa de transferência (0.5%) aplicada: " + taxa);
                    System.out.println("Novo saldo da conta de origem: R$ " + contaOrigem.getSaldo());
                    System.out.println("Novo saldo da conta de destino: R$ " + contaDestino.getSaldo());
                } else {
                    System.out.println("Saldo insuficiente para realizar a transferência incluindo a taxa.");
                }
            } else if (tipoDeCliente == TipoDeCliente.PESSOAFISICA) {
                if (saldoOrigem.compareTo(valorTransferencia) <= 0) {
                    contaOrigem.sacar(valorTransferencia);
                    contaDestino.depositar(valorTransferencia);
                    System.out.println("Transferência realizada com sucesso!");
                    System.out.println("Novo saldo da conta de origem: R$ " + contaOrigem.getSaldo());
                    System.out.println("Novo saldo da conta de destino: R$ " + contaDestino.getSaldo());
                } else {
                    System.out.println("Saldo insuficiente para realizar a transferência.");
                }
            }
        }
    }

    public static void investir(Scanner scanner, ArrayList<Conta> contas) {
        System.out.print("Digite o número da conta para investimento: ");
        String numeroConta = scanner.next();
        Conta conta = null;
        for (Conta c : contas) {
            if (c.getNumeroDaConta().equals(numeroConta)) {
                conta = c;
                break;
            }
        }
        if (conta == null) {
            System.out.println("Conta não encontrada.");
            return;
        }

        System.out.print("Digite o valor do investimento: R$ ");
        BigDecimal valorInvestimento = scanner.nextBigDecimal();

        TipoDeCliente tipoDeCliente = conta.getCliente().getTipoDeCliente();
        BigDecimal taxaDeRendimento = new BigDecimal("0.02");

        if (tipoDeCliente == TipoDeCliente.PESSOAJURIDICA) {

            taxaDeRendimento = taxaDeRendimento.add(new BigDecimal("0.02"));
        }

        BigDecimal rendimento = valorInvestimento.multiply(taxaDeRendimento);
        BigDecimal novoSaldoInvestimento = conta.getSaldo().add(rendimento);
        BigDecimal novoSaldo = novoSaldoInvestimento.add(valorInvestimento);
        conta.depositar(novoSaldo);
        System.out.println("Investimento realizado com sucesso!");
        System.out.println("Rendimento: R$ " + rendimento);
        System.out.println("Novo saldo da conta: R$ " + novoSaldo);
        System.out.println("                                                           " +
                "                                                                    " +
                "                                                                                     " +
                "         " + Cores.TEXT_RESET);
    }
}