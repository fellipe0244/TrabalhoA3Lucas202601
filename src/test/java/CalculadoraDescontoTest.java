

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


public class CalculadoraDescontoTest {

    @Test
    public void deveAplicarDescontoFixoCorretamente() {
        
        CalculadoraDesconto calc = new CalculadoraDesconto(new DescontoFixo());
        double resultado = calc.aplicar(100.0, 20.0);
        
        Assertions.assertEquals(80.0, resultado, 0.001);
    }

    @Test
    public void deveGarantirQueDescontoFixoNaoDeixeValorNegativo() {
        
        CalculadoraDesconto calc = new CalculadoraDesconto(new DescontoFixo());
        double resultado = calc.aplicar(10.0, 50.0);
        
        Assertions.assertEquals(0.0, resultado, 0.001);
    }

    @Test
    public void deveAplicarDescontoPercentualCorretamente() {
        
        CalculadoraDesconto calc = new CalculadoraDesconto(new DescontoPercentual());
        double resultado = calc.aplicar(200.0, 10.0);
        
    
        Assertions.assertEquals(180.0, resultado, 0.001);
    }
}
