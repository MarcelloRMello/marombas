package com.example.demo;

// Classe para Acessórios (Herança de Produto)
class Acessorio extends Produto {
    private String material;
    private String utilidade; // Ex: Coqueteleira, Cinta de Treino

    public Acessorio(int id, String nome, double preco, int quantidadeEstoque, String material, String utilidade) {
        super(id, nome, preco, quantidadeEstoque);
        this.material = material;
        this.utilidade = utilidade;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getUtilidade() {
        return utilidade;
    }

    public void setUtilidade(String utilidade) {
        this.utilidade = utilidade;
    }

    @Override
    public String obterInformacoesAdicionais() {
        return "Material: " + material + ", Utilidade: " + utilidade;
    }
}