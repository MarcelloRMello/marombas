package com.example.demo;

// Classe de Exceção Personalizada (Herança de Exception)
class ProdutoNaoEncontradoException extends Exception {
    public ProdutoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}