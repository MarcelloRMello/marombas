package com.example.demo;

// Classe para Suplementos (Herança de Produto)
class Suplemento extends Produto {
    private String sabor;
    private String tipo; // Ex: Proteína, Creatina, Termogênico

    public Suplemento(int id, String nome, double preco, int quantidadeEstoque, String sabor, String tipo) {
        super(id, nome, preco, quantidadeEstoque);
        this.sabor = sabor;
        this.tipo = tipo;
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String obterInformacoesAdicionais() {
        return "Sabor: " + sabor + ", Tipo: " + tipo;
    }
}
