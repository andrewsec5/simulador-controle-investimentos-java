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

    public static BigDecimal opcoesInvestimento(BigDecimal saldo, BigDecimal entrada, BigDecimal rendimento, BigDecimal taxa, byte meses) {
        rendimento = rendimento.add(entrada.multiply(taxa.pow(meses)).setScale(2, RoundingMode.HALF_UP));
        return rendimento;
    }


    public static void main(String[] args) {

        String entrada;
        String nome;
        byte opcao = 1;
        byte mes1 = 0;
        byte mes2 = 0;
        byte mes3 = 0;
        BigDecimal invPou = BigDecimal.ZERO;
        BigDecimal invCdb = BigDecimal.ZERO;
        BigDecimal invTes = BigDecimal.ZERO;
        BigDecimal entPou;
        BigDecimal entCdb;
        BigDecimal entTes;
        BigDecimal rendPou = BigDecimal.ZERO;
        BigDecimal rendCdb = BigDecimal.ZERO;
        BigDecimal rendTes = BigDecimal.ZERO;
        BigDecimal taxaPou = new BigDecimal("1.005");
        BigDecimal taxaCdb = new BigDecimal("1.012");
        BigDecimal taxaTes = new BigDecimal("1.008");
        BigDecimal saldoAjustado;

        validacaoInicial();

        System.out.print("Informe seu nome: ");
        nome = scanner.nextLine();
        saldoAjustado = validacao(scanner, "Insira o saldo disponivel para investimento: R$");

        while (opcao != 0) {
            System.out.println("\nMENU DE INVESTIMENTO\nUsuário: " + nome + "\nSaldo disponivel: R$" + saldoAjustado);
            System.out.println("\nAplicações:\n1 - Poupança (0,5% a.m.)\n2 - CDB (1,2% a.m.)\n3 - Tesouro Direto (0,8% a.m.)\n0 - Encerrar e gerar relatório.");
            System.out.print("Opção: ");
            try {
                entrada = scanner.nextLine();
                opcao = Byte.parseByte(entrada);
                if (opcao == 1) {
                    System.out.println("\nPOUPANÇA\nSaldo: R$" + saldoAjustado);
                    entPou = validacaoInvestimento(saldoAjustado);
                    invPou = invPou.add(entPou);
                    mes1 = valMes(scanner, "Insira o prazo de investimento (meses): ");
                    rendPou = opcoesInvestimento(saldoAjustado, entPou, rendPou, taxaPou, mes1);
                    saldoAjustado = saldoAjustado.subtract(entPou);
                }
                if (opcao == 2) {
                    System.out.println("\nCDB\nSaldo: R$" + saldoAjustado);
                    entCdb = validacaoInvestimento(saldoAjustado);
                    invCdb = invCdb.add(entCdb);
                    mes2 = valMes(scanner, "Insira o prazo de investimento (meses): ");
                    rendCdb = opcoesInvestimento(saldoAjustado, entCdb, rendCdb, taxaCdb, mes2);
                    saldoAjustado = saldoAjustado.subtract(entCdb);
                }
                if (opcao == 3) {
                    System.out.println("\nTESOURO DIRETO\nSaldo: R$" + saldoAjustado);
                    entTes = validacaoInvestimento(saldoAjustado);
                    invTes = invTes.add(entTes);
                    mes3 = valMes(scanner, "Insira o prazo de investimento (meses): ");
                    rendTes = opcoesInvestimento(saldoAjustado, entTes, rendTes, taxaTes, mes3);
                    saldoAjustado = saldoAjustado.subtract(entTes);
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite 1, 2, 3 ou 0.");
            }
        }
        System.out.println("\nRELATÓRIO GERAL\nSaldo restante: R$" + saldoAjustado);
        System.out.println();
        if (mes1 > 0) {
            System.out.println("Poupança:\nInvestimento inicial --> R$" + invPou + "\nRendimento em " + mes1 + " meses --> R$" + rendPou + "\n");
        }
        if (mes2 > 0) {
            System.out.println("CDB:\nInvestimento inicial --> R$" + invCdb + "\nRendimento em " + mes2 + " meses --> R$" + rendCdb + "\n");
        }
        if (mes3 > 0) {
            System.out.println("Tesouro Direto:\nInvestimento inicial --> R$" + invTes + "\nRendimento em " + mes3 + " meses --> R$" + rendTes + "\n");
        }
        if (mes1 == 0 && mes2 == 0 && mes3 == 0) {
            System.out.println("Nenhum investimento aplicado.");
        }
        scanner.close();
    }
}
