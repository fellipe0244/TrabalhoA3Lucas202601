package br.com.ecommerce.cupom;

public class PagamentoCredito implements FormaPagamento {
    private int parcelas;
    private static final double JUROS = 0.02; // foi escolhido 2% so para testes 

    public PagamentoCredito(int parcelas) {
        this.parcelas = parcelas;
    }

    @Override
    public double aplicarPagamento(double valor) {
        // calculo simples de juros 
        return valor + (valor * (parcelas * JUROS));
    }
}
//classe implementada para calculos na forma de pagamento credito 