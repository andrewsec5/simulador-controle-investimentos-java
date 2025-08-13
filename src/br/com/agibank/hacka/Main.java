package br.com.agibank.hacka;

import java.util.Scanner;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Main {

    public static BigDecimal validacao(Scanner scanner, String msg) {
        boolean val = false;
        BigDecimal valor = null;
        String entrada;

        while (!val) {
            System.out.print(msg);
            entrada = scanner.nextLine().replace(",", ".");
            if (entrada.matches("\\d+(\\.\\d{1,2})?")) {
                valor = new BigDecimal(entrada).setScale(2, RoundingMode.HALF_UP);
                if (valor.compareTo(BigDecimal.ZERO) <= 0) {
                    System.out.println("O valor informado deve ser maior que 0.\n");
                } else {
                    val = true;
                }
            } else {
                System.out.println("Entrada inválida! Digite um número positivo com no máximo duas casas decimais.\n");
            }
        }
        return valor;
    }

    public static byte valMes(Scanner scanner, String msg) {
        String entrada;
        byte mes = 0;
        boolean val = false;

        while (!val) {
            try {
                System.out.print(msg);
                entrada = scanner.nextLine();
                mes = Byte.parseByte(entrada);
                if (mes <= 0) {
                    System.out.println("Insira um prazo válido! Digite um número positivo.\n");
                } else {
                    val = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Formato inválido! Digite um número positivo.\n");
            }
        }
        return mes;
    }

    public static Scanner scanner = new Scanner(System.in);

    public static void validacaoInicial() {
        byte op1 = 0;
        String entrada1;
        while (op1 != 1) {
            System.out.println("Bem vindo, essa é uma ferramenta de controle de investimentos pessoais.\nPara avançar, confirme ser uma pessoa física: ");
            System.out.println("1 - Sou pessoa física\n0 - NÃO sou pessoa física");
            System.out.print("Opção: ");
            try {
                entrada1 = scanner.nextLine();
                op1 = Byte.parseByte(entrada1);
                if (op1 == 1) {
                    System.out.println("Acesso permitido.\n");
                } else if (op1 == 0) {
                    System.out.println("Acesso negado. \nEncerrando a aplicação...");
                    System.exit(0);
                } else {
                    System.out.println("Opção Inválida! Selecione 1 ou 0.\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite apenas 1 ou 0.\n");
            }
        }
    }

    public static BigDecimal validacaoInvestimento(BigDecimal saldo) {
        BigDecimal entrada = BigDecimal.ZERO;
        boolean validacao = false;
        while (!validacao) {
            entrada = validacao(scanner, "Informe o valor do investimento: R$");
            if (entrada.compareTo(saldo) > 0) {
                System.out.println("Saldo insuficiente! Insira um valor menor.");
            } else {
                validacao = true;
            }
        }
        return entrada;
    }

    public static BigDecimal rendimento(BigDecimal entrada, BigDecimal rendimento, BigDecimal taxa, byte meses) {
        rendimento = rendimento.add(entrada.multiply(taxa.pow(meses)).setScale(2, RoundingMode.HALF_UP));
        return rendimento;
    }

    public static BigDecimal menuInvestimento (BigDecimal[] investimentos, BigDecimal[] rendimentos, byte[] meses, BigDecimal saldo, String nome){
        byte opcao = 1;
        BigDecimal taxaPou = BigDecimal.valueOf(1.005);
        BigDecimal taxaCdb = BigDecimal.valueOf(1.012);
        BigDecimal taxaTes = BigDecimal.valueOf(1.008);
        String entrada;
        while (opcao != 0) {
            BigDecimal entPou;
            BigDecimal entCdb;
            BigDecimal entTes;

            System.out.println("\nMENU DE INVESTIMENTO\nUsuário: " + nome + "\nSaldo disponivel: R$" + saldo);
            System.out.println("\nAplicações:\n1 - Poupança (0,5% a.m.)\n2 - CDB (1,2% a.m.)\n3 - Tesouro Direto (0,8% a.m.)\n0 - Encerrar e gerar relatório.");
            System.out.print("Opção: ");
            try {
                entrada = scanner.nextLine();
                opcao = Byte.parseByte(entrada);
                if (opcao == 1) {
                    System.out.println("\nPOUPANÇA\nSaldo: R$" + saldo);
                    entPou = validacaoInvestimento(saldo);
                    investimentos[0] = investimentos[0].add(entPou);
                    meses[0] = valMes(scanner, "Insira o prazo de investimento (meses): ");
                    rendimentos[0] = rendimento(entPou, rendimentos[0], taxaPou, meses[0]);
                    saldo = saldo.subtract(entPou);
                }
                if (opcao == 2) {
                    System.out.println("\nCDB\nSaldo: R$" + saldo);
                    entCdb = validacaoInvestimento(saldo);
                    investimentos[1] = investimentos[1].add(entCdb);
                    meses[1] = valMes(scanner, "Insira o prazo de investimento (meses): ");
                    rendimentos[1] = rendimento(entCdb, rendimentos[1], taxaCdb, meses[1]);
                    saldo = saldo.subtract(entCdb);
                }
                if (opcao == 3) {
                    System.out.println("\nTESOURO DIRETO\nSaldo: R$" + saldo);
                    entTes = validacaoInvestimento(saldo);
                    investimentos[2] = investimentos[2].add(entTes);
                    meses[2] = valMes(scanner, "Insira o prazo de investimento (meses): ");
                    rendimentos[2] = rendimento(entTes, rendimentos[2], taxaTes, meses[2]);
                    saldo = saldo.subtract(entTes);
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite 1, 2, 3 ou 0.");
            }
        }
        return saldo;
    }

    public static void relatorioInvestimentos(BigDecimal[] investimentos, BigDecimal[] rendimentos, BigDecimal saldo){
        System.out.println("\nRELATÓRIO GERAL\nSaldo restante: R$" + saldo);
        System.out.println();
        if (investimentos[0].compareTo(BigDecimal.ZERO) > 0) {
            System.out.println("Poupança:\nInvestimento inicial --> R$" + investimentos[0] + "\nRendimento final --> R$" + rendimentos[0] + "\n");
        }
        if (investimentos[1].compareTo(BigDecimal.ZERO) > 0) {
            System.out.println("CDB:\nInvestimento inicial --> R$" + investimentos[1] + "\nRendimento final --> R$" + rendimentos[1] + "\n");
        }
        if (investimentos[2].compareTo(BigDecimal.ZERO) > 0) {
            System.out.println("Tesouro Direto:\nInvestimento inicial --> R$" + investimentos[2] + "\nRendimento final --> R$" + rendimentos[2] + "\n");
        }
        if (investimentos[0].compareTo(BigDecimal.ZERO) == 0 && investimentos[1].compareTo(BigDecimal.ZERO) == 0 && investimentos[2].compareTo(BigDecimal.ZERO) == 0) {
            System.out.println("Nenhum investimento aplicado.");
        }
    }

    public static void main(String[] args) {

        String nome;
        byte[] meses = new byte[3];
        BigDecimal[] investimentos = {BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO};
        BigDecimal[] rendimentos = {BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO};
        BigDecimal saldoAjustado = BigDecimal.ZERO;

        validacaoInicial();

        System.out.print("Informe seu nome: ");
        nome = scanner.nextLine();
        saldoAjustado = validacao(scanner, "Insira o saldo disponivel para investimento: R$");

        saldoAjustado = menuInvestimento(investimentos, rendimentos, meses, saldoAjustado, nome);

        relatorioInvestimentos(investimentos, rendimentos, saldoAjustado);

        scanner.close();
    }
}
