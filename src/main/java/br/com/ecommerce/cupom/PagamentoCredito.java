package br.com.ecommerce.cupom;

public class PagamentoCredito implements FormaPagamento {
    private int parcelas;
    private static final double JUROS = 0.02; // 2% ao mês

    public PagamentoCredito(int parcelas) {
        this.parcelas = parcelas;
    }

    @Override
    public double aplicarPagamento(double valor) {
        // Cálculo simples de juros compostos ou simples (ex: juros simples)
        return valor + (valor * (parcelas * JUROS));
    }
}