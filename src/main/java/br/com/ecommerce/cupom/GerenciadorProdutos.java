package br.com.ecommerce.cupom;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.ArrayList;
import java.util.List; // IMPORTANTE: Adicione isso

public class GerenciadorProdutos {

    private static final String ARQUIVO = "produtos.json";
    private ObjectMapper mapper = new ObjectMapper();

    public void salvarProdutos(List<Produto> produtos) throws Exception {
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(ARQUIVO), produtos);
        System.out.println("[LOG] Salvando catálogo... Total de itens: " + produtos.size());
    }

    public List<Produto> carregarProdutos() {
        try {
            File file = new File(ARQUIVO);
            System.out.println("[LOG] Carregando catálogo do JSON...");
            if (!file.exists()) return new ArrayList<>();
            return mapper.readValue(file, new TypeReference<List<Produto>>(){});
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        
    }
}
