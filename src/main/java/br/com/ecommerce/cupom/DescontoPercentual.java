package br.com.ecommerce.cupom;

public class DescontoPercentual implements RegraDesconto {
    @Override
    public double calcularDesconto(double valorTotal, double fatorDesconto) {
        if (fatorDesconto < 0 || fatorDesconto > 100) {
            throw new IllegalArgumentException("Percentual de desconto inválido.");
        }
        return valorTotal - (valorTotal * (fatorDesconto / 100.0));
    }
}
