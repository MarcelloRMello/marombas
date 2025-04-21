package com.example.demo;

// Classe base para todos os produtos de estoque
class Produto {
    private int id;
    private String nome;
    private double preco;
    private int quantidadeEstoque;

    public Produto(int id, String nome, double preco, int quantidadeEstoque) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    // Getters (Encapsulamento)
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    // Setters (Encapsulamento)
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    // -------------------- 5. PRINCIPAIS MÉTODOS DE OBJETOS EM JAVA --------------------

    @Override
    public String toString() {
        return "ID: " + id + ", Nome: " + nome + ", Preço: R$" + String.format("%.2f", preco) + ", Estoque: " + quantidadeEstoque;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Produto produto = (Produto) obj;
        return id == produto.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    // Método comum a todos os produtos (Polimorfismo - será sobrescrito nas subclasses)
    public String obterInformacoesAdicionais() {
        return "Nenhuma informação adicional.";
    }

    // Setters (Encapsulamento)
    public void setId(int id) { // Adicionando o setId
        this.id = id;
    }
}
