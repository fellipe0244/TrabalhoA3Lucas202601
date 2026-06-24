package br.com.ecommerce.cupom;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(() -> {
            new JanelaPrincipal();
        });
        
        // Toda a interação será feita dentro da JanelaPrincipal.
    }
}