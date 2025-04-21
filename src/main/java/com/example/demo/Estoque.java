package com.example.demo;

import java.util.ArrayList;
import java.util.List;

class Estoque implements OperacoesEstoque {
    private List<Produto> produtos = new ArrayList<>();
    private int proximoId = 1;

    @Override
    public void adicionarProduto(Produto produto) {
        produto.setId(proximoId++);
        produtos.add(produto);
        System.out.println("Produto adicionado ao estoque.");
    }

    @Override
    public void removerProduto(int id) throws ProdutoNaoEncontradoException {
        Produto produtoRemover = buscarProduto(id);
        if (produtoRemover != null) {
            produtos.remove(produtoRemover);
            System.out.println("Produto com ID " + id + " removido do estoque.");
        } else {
            throw new ProdutoNaoEncontradoException("Produto com ID " + id + " não encontrado no estoque.");
        }
    }

    @Override
    public Produto buscarProduto(int id) throws ProdutoNaoEncontradoException {
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                return produto;
            }
    }
    // Certifique-se de que esta linha existe para lançar a exceção
    throw new ProdutoNaoEncontradoException("Produto com ID " + id + " não encontrado no estoque.");
}

    @Override
    public void atualizarProduto(Produto produtoAtualizado) throws ProdutoNaoEncontradoException {
        Produto produtoExistente = buscarProduto(produtoAtualizado.getId());
        if (produtoExistente != null) {
            produtoExistente.setNome(produtoAtualizado.getNome());
            produtoExistente.setPreco(produtoAtualizado.getPreco());
            produtoExistente.setQuantidadeEstoque(produtoAtualizado.getQuantidadeEstoque());
            if (produtoAtualizado instanceof Suplemento && produtoExistente instanceof Suplemento) {
                ((Suplemento) produtoExistente).setSabor(((Suplemento) produtoAtualizado).getSabor());
                ((Suplemento) produtoExistente).setTipo(((Suplemento) produtoAtualizado).getTipo());
            } else if (produtoAtualizado instanceof Acessorio && produtoExistente instanceof Acessorio) {
                ((Acessorio) produtoExistente).setMaterial(((Acessorio) produtoAtualizado).getMaterial());
                ((Acessorio) produtoExistente).setUtilidade(((Acessorio) produtoAtualizado).getUtilidade());
            }
            System.out.println("Produto com ID " + produtoAtualizado.getId() + " atualizado.");
        } else {
            throw new ProdutoNaoEncontradoException("Produto com ID " + produtoAtualizado.getId() + " não encontrado no estoque.");
        }
    }

    @Override
    public void listarProdutos() {
        if (produtos.isEmpty()) {
            System.out.println("O estoque está vazio.");
        } else {
            System.out.println("--- Produtos no Estoque ---");
            for (Produto produto : produtos) {
                System.out.println(produto + " - " + produto.obterInformacoesAdicionais());
            }
        }
    }
}
