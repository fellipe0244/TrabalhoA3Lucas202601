package br.com.ecommerce.cupom;


public class DescontoFactory {
public static RegraDesconto criarRegra(TipoDesconto tipo) {
        return switch (tipo) {
            case FIXO -> new DescontoFixo();
            case PERCENTUAL -> new DescontoPercentual();
            default -> throw new IllegalArgumentException("Tipo de desconto não suportado.");
        };
    }
}

// classe que decide a regra de desconto com base no que é solicitado