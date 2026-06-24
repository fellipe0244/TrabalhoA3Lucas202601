package br.com.ecommerce.cupom;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class CalculadoraDescontoTest {

    // cenario de desconto 

    @Test
    public void deveAplicarDescontoFixoCorretamente() {
        System.out.println("\n=== Cenário 2: Desconto Fixo Simples ===");
        RegraDesconto regra = DescontoFactory.criarRegra(TipoDesconto.FIXO);
        CalculadoraDesconto calc = new CalculadoraDesconto(regra);
        assertEquals(80.0, calc.aplicar(100.0, 20.0), 0.001);
    }

    @Test
    public void deveAplicarDescontoPercentualCorretamente() {
        System.out.println("\n=== Cenário 5: Desconto Percentual Simples ===");
        RegraDesconto regra = DescontoFactory.criarRegra(TipoDesconto.PERCENTUAL);
        CalculadoraDesconto calc = new CalculadoraDesconto(regra);
        assertEquals(180.0, calc.aplicar(200.0, 10.0), 0.001);
    }

    @Test
    public void deveAplicarDescontoFixoEmUmProdutoEspecifico() {
        System.out.println("\n=== Cenário 1: Cupom Fixo em Produto Real ===");
        Produto smartphone = new Produto("iPhone 15 Pro Max", 7500.00);
        RegraDesconto regra = DescontoFactory.criarRegra(TipoDesconto.FIXO);
        CalculadoraDesconto calc = new CalculadoraDesconto(regra);
        
        double valorFinal = calc.aplicarNoProduto(smartphone, 500.0);
        assertEquals(7000.00, valorFinal, 0.001);
    }

    // --- cenarios de pagamento 

    @Test
    public void deveCalcularJurosDeCreditoCorretamente() {
        System.out.println("\n=== Cenário 4: Cálculo de Juros no Crédito ===");
        FormaPagamento pagamento = PagamentoFactory.criarPagamento("CREDITO", 3);
        double valorFinal = pagamento.aplicarPagamento(100.0);
        
        System.out.println("[LOG] Valor final com 6% de juros: R$ " + valorFinal);
        assertEquals(106.0, valorFinal, 0.001);
    }

    // --- cenarios de persistencia ---

    @Test
    public void deveSalvarERemoverProdutoDoCatalogo() throws Exception {
        System.out.println("\n=== Cenário 3: Persistência no Catálogo (JSON) ===");
        GerenciadorProdutos ger = new GerenciadorProdutos();
        Produto p = new Produto("TesteIntegracao", 150.0);
        
        List<Produto> lista = ger.carregarProdutos();
        lista.add(p);
        ger.salvarProdutos(lista);
        
        List<Produto> listaRecarregada = ger.carregarProdutos();
        assertTrue(listaRecarregada.contains(p), "O produto deveria estar no arquivo JSON");
        
        listaRecarregada.remove(p);
        ger.salvarProdutos(listaRecarregada);
        
        assertFalse(ger.carregarProdutos().contains(p), "O produto deveria ter sido removido");
        System.out.println("[LOG] Teste de persistência finalizado com sucesso.");
    }
}

//aqui é pra rodar o mvn test direto na pasta raiz do projeto 