package br.com.ecommerce.cupom;

public class PagamentoPadrao implements FormaPagamento {
    
    @Override
    public double aplicarPagamento(double valor) {
        return valor; // Sem juros nem desconto adicional
    }
}
