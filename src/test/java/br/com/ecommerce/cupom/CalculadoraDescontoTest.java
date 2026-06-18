package br.com.ecommerce.cupom;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CalculadoraDescontoTest {

    // --- TESTES ATUAIS (MANTIDOS) ---
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

    // --- NOVOS TESTES DINÂMICOS (PRODUTOS CUSTOMIZADOS) ---

    @Test
    public void deveAplicarDescontoFixoEmUmProdutoEspecifico() {
        System.out.println("\n=== [TESTE EXTRA] Cenário 4: Cupom Fixo em Produto Real ===");
        
        // Aqui você pode mexer e escolher o produto e o valor original que quiser!
        Produto smartphone = new Produto("iPhone 15 Pro Max", 7500.00);
        double cupomFixo = 500.00; // Desconto de R$ 500
        
        RegraDesconto regra = DescontoFactory.criarRegra(TipoDesconto.FIXO);
        CalculadoraDesconto calc = new CalculadoraDesconto(regra);
        
        double valorFinal = calc.aplicarNoProduto(smartphone, cupomFixo);
        
        // 7500 - 500 = 7000 esperado
        assertEquals(7000.00, valorFinal, 0.001);
    }

    @Test
    public void deveAplicarDescontoPercentualEmUmProdutoEspecifico() {
        System.out.println("\n=== [TESTE EXTRA] Cenário 5: Cupom Percentual em Produto Real ===");
        
        // Outro produto que você pode alterar o valor original para testar
        Produto foneOuvido = new Produto("Headphone Sony WH-1000XM5", 2000.00);
        double cupomPorcentagem = 20.0; // 20% de desconto
        
        RegraDesconto regra = DescontoFactory.criarRegra(TipoDesconto.PERCENTUAL);
        CalculadoraDesconto calc = new CalculadoraDesconto(regra);
        
        double valorFinal = calc.aplicarNoProduto(foneOuvido, cupomPorcentagem);
        
        // 20% de 2000 é 400. Valor final esperado: 1600
        assertEquals(1600.00, valorFinal, 0.001);
    }
}