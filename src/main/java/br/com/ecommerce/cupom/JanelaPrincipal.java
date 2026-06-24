package br.com.ecommerce.cupom;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class JanelaPrincipal extends JFrame {
    // Componentes de Cálculo
    private JTextField txtNome = new JTextField(15);
    private JTextField txtPreco = new JTextField(10);
    private JTextField txtFator = new JTextField(10);
    private JComboBox<TipoDesconto> comboTipo = new JComboBox<>(TipoDesconto.values());
    private JComboBox<String> comboPagamento = new JComboBox<>(new String[]{"PIX", "DEBITO", "DINHEIRO", "CREDITO"});
    
    private JButton btnCalcular = new JButton("Calcular Desconto");
    private JLabel lblResultado = new JLabel("Resultado: R$ 0.00", SwingConstants.CENTER);

    // Componentes de Catálogo
    private JTextField txtNovoNome = new JTextField(10);
    private JTextField txtNovoPreco = new JTextField(10);
    private JComboBox<Produto> comboCatalogo = new JComboBox<>();
    private JButton btnCadastrar = new JButton("Salvar no Catálogo");
    private JButton btnRemover = new JButton("Remover Selecionado");
    
    private GerenciadorProdutos gerenciador = new GerenciadorProdutos();

    public JanelaPrincipal() {
        setTitle("Simulador de Descontos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Painel Esquerdo: Catálogo
        JPanel painelCatalogo = new JPanel(new GridLayout(8, 1, 5, 5));
        painelCatalogo.setBorder(BorderFactory.createTitledBorder("Gerenciar Catálogo"));
        painelCatalogo.add(new JLabel("Novo Nome:")); painelCatalogo.add(txtNovoNome);
        painelCatalogo.add(new JLabel("Novo Preço:")); painelCatalogo.add(txtNovoPreco);
        painelCatalogo.add(btnCadastrar);
        painelCatalogo.add(new JLabel("Selecionar Produto:"));
        painelCatalogo.add(comboCatalogo);
        painelCatalogo.add(btnRemover);

        // Painel Direito: Cálculo
        JPanel painelCalculo = new JPanel(new GridLayout(7, 2, 5, 5));
        painelCalculo.setBorder(BorderFactory.createTitledBorder("Cálculo"));
        painelCalculo.add(new JLabel("Nome:")); painelCalculo.add(txtNome);
        painelCalculo.add(new JLabel("Preço:")); painelCalculo.add(txtPreco);
        painelCalculo.add(new JLabel("Tipo:")); painelCalculo.add(comboTipo);
        painelCalculo.add(new JLabel("Pagamento:")); painelCalculo.add(comboPagamento);
        painelCalculo.add(new JLabel("Desconto:")); painelCalculo.add(txtFator);
        painelCalculo.add(btnCalcular); painelCalculo.add(lblResultado);

        comboCatalogo.addActionListener(e -> {
            Produto p = (Produto) comboCatalogo.getSelectedItem();
            if (p != null) {
                txtNome.setText(p.getNome());
                txtPreco.setText(String.format("%.2f", p.getPreco()));
            }
        });

        btnCadastrar.addActionListener(e -> salvarNovoProduto());
        btnRemover.addActionListener(e -> removerProduto());
        btnCalcular.addActionListener(e -> calcular());

        add(painelCatalogo, BorderLayout.WEST);
        add(painelCalculo, BorderLayout.CENTER);

        atualizarCombo();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void calcular() {
        try {
            double preco = Double.parseDouble(txtPreco.getText().replace(",", "."));
            double fator = Double.parseDouble(txtFator.getText().replace(",", "."));
            TipoDesconto tipo = (TipoDesconto) comboTipo.getSelectedItem();

            RegraDesconto regra = DescontoFactory.criarRegra(tipo);
            CalculadoraDesconto calc = new CalculadoraDesconto(regra);
            Produto p = new Produto(txtNome.getText(), preco);
            double valorPosDesconto = calc.aplicarNoProduto(p, fator);

            String metodo = (String) comboPagamento.getSelectedItem();
            int parcelas = 0;
            if ("CREDITO".equals(metodo)) {
                String input = JOptionPane.showInputDialog(this, "Parcelas (Juros 2% a.m.):");
                parcelas = (input != null && !input.isEmpty()) ? Integer.parseInt(input) : 0;
            }

            FormaPagamento forma = PagamentoFactory.criarPagamento(metodo, parcelas);
            double totalFinal = forma.aplicarPagamento(valorPosDesconto);
            
            // Logs de monitoramento
            System.out.println("[LOG] Calculando: " + p.getNome() + " | Pagamento: " + metodo);
            System.out.println("[LOG] Total Final: R$ " + String.format("%.2f", totalFinal));
            
            lblResultado.setText(String.format("Resultado: R$ %.2f", totalFinal));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: Verifique os campos de cálculo.");
            System.out.println("[LOG] Erro no cálculo: " + ex.getMessage());
        }
    }

    private void salvarNovoProduto() {
        try {
            String nome = txtNovoNome.getText();
            double preco = Double.parseDouble(txtNovoPreco.getText().replace(",", "."));
            Produto p = new Produto(nome, preco);
            List<Produto> lista = gerenciador.carregarProdutos();
            lista.add(p);
            gerenciador.salvarProdutos(lista);
            atualizarCombo();
            System.out.println("[LOG] Produto salvo: " + nome);
            JOptionPane.showMessageDialog(this, "Produto cadastrado!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar!");
        }
    }

    private void removerProduto() {
        Produto p = (Produto) comboCatalogo.getSelectedItem();
        if (p != null) {
            try {
                List<Produto> lista = gerenciador.carregarProdutos();
                lista.removeIf(prod -> prod.equals(p));
                gerenciador.salvarProdutos(lista);
                atualizarCombo();
                System.out.println("[LOG] Produto removido: " + p.getNome());
                JOptionPane.showMessageDialog(this, "Produto removido!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro ao remover!");
            }
        }
    }

    private void atualizarCombo() {
        comboCatalogo.removeAllItems();
        for (Produto p : gerenciador.carregarProdutos()) {
            comboCatalogo.addItem(p);
        }
    }
}