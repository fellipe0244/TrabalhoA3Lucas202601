package br.com.ecommerce.cupom;

public class PagamentoPadrao implements FormaPagamento {
    
    @Override
    public double aplicarPagamento(double valor) {
        return valor; // sem juros nem desconto adicional
    }
}
//classe criada para calculos na forma de pagamento sem juros