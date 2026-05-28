

public class CalculadoraDesconto {
    private final RegraDesconto regraDesconto;

   
    public CalculadoraDesconto(RegraDesconto regraDesconto) {
        this.regraDesconto = regraDesconto;
    }

    public double aplicar(double valorTotal, double fatorDesconto) {
        return regraDesconto.calcularDesconto(valorTotal, fatorDesconto);
    }
}