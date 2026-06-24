package br.com.ecommerce.cupom;

public class Produto {
    
    private String nome; 
    private double preco;

    public Produto() {}

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    //get e set que preenche json 
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return nome.equals(produto.nome);
    }

    @Override
    public String toString() {
        return nome + " - R$ " + String.format("%.2f", preco);
    }
}