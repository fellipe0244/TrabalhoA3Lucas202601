package br.com.ecommerce.cupom;

public interface RegraDesconto {
    double calcularDesconto(double valorTotal, double fatorDesconto);
}

// classe que define o metodo calcular desconto, todo o sistema depende dip e solid 
// se criar outra categoria so chamar esse metodo