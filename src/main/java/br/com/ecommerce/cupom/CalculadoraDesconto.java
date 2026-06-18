package br.com.ecommerce.cupom;

/**
 * Classe de contexto que utiliza o Padrão de Projeto Strategy.
 * Aplica SRP (Single Responsibility Principle).
 */
public class CalculadoraDesconto {
    private final RegraDesconto regraDesconto;

    public CalculadoraDesconto(RegraDesconto regraDesconto) {
        this.regraDesconto = regraDesconto;
    }

    public double aplicar(double valorTotal, double fatorDesconto) {
        System.out.println("[INFO] [CalculadoraDesconto] Iniciando processo de validação de cupom.");
        System.out.printf("[INFO] [CalculadoraDesconto] Valor original do carrinho: R$ %.2f\n", valorTotal);
        
        if (valorTotal <= 0) {
            System.out.println("[WARN] [CalculadoraDesconto] Tentativa de aplicação em carrinho com valor zerado ou inválido.");
            return 0;
        }

        // Executa a estratégia injetada
        double valorFinal = regraDesconto.calcularDesconto(valorTotal, fatorDesconto);
        
        System.out.printf("[INFO] [CalculadoraDesconto] Estratégia '%s' aplicada com sucesso.\n", regraDesconto.getClass().getSimpleName());
        System.out.printf("[INFO] [CalculadoraDesconto] Valor final com desconto: R$ %.2f\n", valorFinal);
        System.out.println("[INFO] [CalculadoraDesconto] Processo finalizado.\n");
        
        return valorFinal;
    }

    public double aplicarNoProduto(Produto produto, double fatorDesconto) {
        System.out.printf("[INFO] [CalculadoraDesconto] Processing cupom para o produto: '%s'\n", produto.getNome());
        return aplicar(produto.getPreco(), fatorDesconto);
    }

}