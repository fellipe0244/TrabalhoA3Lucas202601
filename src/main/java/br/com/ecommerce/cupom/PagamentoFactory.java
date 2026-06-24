package br.com.ecommerce.cupom;

public class PagamentoFactory {
    public static FormaPagamento criarPagamento(String tipo, int parcelas) {
        return switch (tipo.toUpperCase()) {
            case "PIX", "DINHEIRO", "DEBITO" -> new PagamentoPadrao();
            case "CREDITO" -> new PagamentoCredito(parcelas);
            default -> throw new IllegalArgumentException("Método de pagamento não suportado.");
        };
    }
}
