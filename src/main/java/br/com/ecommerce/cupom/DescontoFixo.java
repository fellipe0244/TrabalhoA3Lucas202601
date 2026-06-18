package br.com.ecommerce.cupom;


public class DescontoFixo implements RegraDesconto {
    @Override
    public double calcularDesconto(double valorTotal, double fatorDesconto) {
        return Math.max(0, valorTotal - fatorDesconto);
    }
}

//aplica desconto valor bruto 
