package br.com.ecommerce.cupom;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("   SIMULADOR DE CHECKOUT INTERATIVO - CUPONS      ");

        // 1. Entrada do Produto
        System.out.print("Digite o nome do produto: ");
        String nomeProduto = scanner.nextLine();

        System.out.print("Digite o valor original do produto (Ex: 150,00): ");
        double precoProduto = scanner.nextDouble();
        
        Produto produto = new Produto(nomeProduto, precoProduto);

        // 2. Escolha do Tipo de Cupom
        System.out.println("\n--- Escolha o tipo de cupom ---");
        System.out.println("[1] FIXO (Desconto em Reais)");
        System.out.println("[2] PERCENTUAL (Desconto em %)");
        System.out.print("Opção desejada: ");
        int opcao = scanner.nextInt();

        TipoDesconto tipoSelected = (opcao == 2) ? TipoDesconto.PERCENTUAL : TipoDesconto.FIXO;

        // 3. Valor do Desconto
        System.out.print("Digite o valor do cupom (Ex: 15 para 15% ou 15 Reais): ");
        double fatorDesconto = scanner.nextDouble();

        System.out.println("  PROCESSANDO SIMULAÇÃO  ");

        // 4. Execução do Motor de Regras (Strategy + Factory)
        RegraDesconto regra = DescontoFactory.criarRegra(tipoSelected);
        CalculadoraDesconto calculadora = new CalculadoraDesconto(regra);
        
        // Aplica e mostra os logs detalhados que criamos
        calculadora.aplicarNoProduto(produto, fatorDesconto);

        System.out.println(" OBRIGADO ");
        scanner.close();
    }
}