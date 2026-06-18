package br.com.ecommerce.cupom;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CalculadoraDescontoTest {

    @Test
    public void deveAplicarDescontoFixoCorretamente() {
        RegraDesconto regra = DescontoFactory.criarRegra(TipoDesconto.FIXO);
        CalculadoraDesconto calc = new CalculadoraDesconto(regra);
        double resultado = calc.aplicar(100.0, 20.0);
        assertEquals(80.0, resultado, 0.001);
    }

    @Test
    public void deveGarantirQueDescontoFixoNaoDeixeValorNegativo() {
        RegraDesconto regra = DescontoFactory.criarRegra(TipoDesconto.FIXO);
        CalculadoraDesconto calc = new CalculadoraDesconto(regra);
        double resultado = calc.aplicar(10.0, 50.0);
        assertEquals(0.0, resultado, 0.001);
    }

    @Test
    public void deveAplicarDescontoPercentualCorretamente() {
        RegraDesconto regra = DescontoFactory.criarRegra(TipoDesconto.PERCENTUAL);
        CalculadoraDesconto calc = new CalculadoraDesconto(regra);
        double resultado = calc.aplicar(200.0, 10.0);
        assertEquals(180.0, resultado, 0.001);
    }

   

    @Test
    public void deveAplicarDescontoFixoEmUmProdutoEspecifico() {
        System.out.println("\n=== Cenário 4: Cupom Fixo em Produto Real ===");
        
        
        Produto smartphone = new Produto("iPhone 15 Pro Max", 7500.00);
        double cupomFixo = 500.00; 
        
        RegraDesconto regra = DescontoFactory.criarRegra(TipoDesconto.FIXO);
        CalculadoraDesconto calc = new CalculadoraDesconto(regra);
        
        double valorFinal = calc.aplicarNoProduto(smartphone, cupomFixo);
        
        
        assertEquals(7000.00, valorFinal, 0.001);
    }

    @Test
    public void deveAplicarDescontoPercentualEmUmProdutoEspecifico() {
        System.out.println("\n===  Cenário 5: Cupom Percentual em Produto Real ===");
        
       
        Produto foneOuvido = new Produto("Headphone Sony WH-1000XM5", 2000.00);
        double cupomPorcentagem = 20.0;
        
        RegraDesconto regra = DescontoFactory.criarRegra(TipoDesconto.PERCENTUAL);
        CalculadoraDesconto calc = new CalculadoraDesconto(regra);
        
        double valorFinal = calc.aplicarNoProduto(foneOuvido, cupomPorcentagem);
        
      
        assertEquals(1600.00, valorFinal, 0.001);
    }
}

//aqui é pra rodar o mvn tst 