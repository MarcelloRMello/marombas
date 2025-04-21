package com.example.demo;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Marombas {
    private static Estoque estoque = new Estoque();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws ProdutoNaoEncontradoException {
        while (true) {
            mostrarMenu();
            int opcao = lerOpcao();

            try {
                switch (opcao) {
                    case 1:
                        adicionarNovoProduto();
                        break;
                    case 2:
                        removerProduto();
                        break;
                    case 3:
                        buscarProduto();
                        break;
                    case 4:
                        atualizarProduto();
                        break;
                    case 5:
                        listarProdutos();
                        break;
                    case 6:
                        System.out.println("Saindo do sistema de estoque.");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Erro: Entrada inválida. Por favor, digite um número inteiro.");
                scanner.next(); // Limpar o buffer do scanner
            } catch (Exception e) {
                System.err.println("Ocorreu um erro inesperado: " + e.getMessage());
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n--- Sistema de Estoque de Suplementos ---");
        System.out.println("1. Adicionar Novo Produto");
        System.out.println("2. Remover Produto");
        System.out.println("3. Buscar Produto");
        System.out.println("4. Atualizar Produto");
        System.out.println("5. Listar Produtos");
        System.out.println("6. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static int lerOpcao() {
        return scanner.nextInt();
    }

    private static void adicionarNovoProduto() {
        System.out.println("\n--- Adicionar Novo Produto ---");
        System.out.println("Tipo de produto (1 - Suplemento, 2 - Acessório):");
        int tipoProduto = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Preço: ");
        double preco = scanner.nextDouble();
        System.out.print("Quantidade em Estoque: ");
        int quantidadeEstoque = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        if (tipoProduto == 1) {
            System.out.print("Sabor: ");
            String sabor = scanner.nextLine();
            System.out.print("Tipo (Proteína, Creatina, Termogênico): ");
            String tipoSuplemento = scanner.nextLine();
            estoque.adicionarProduto(new Suplemento(0, nome, preco, quantidadeEstoque, sabor, tipoSuplemento));
        } else if (tipoProduto == 2) {
            System.out.print("Material: ");
            String material = scanner.nextLine();
            System.out.print("Utilidade (Coqueteleira, Cinta de Treino): ");
            String utilidade = scanner.nextLine();
            estoque.adicionarProduto(new Acessorio(0, nome, preco, quantidadeEstoque, material, utilidade));
        } else {
            System.out.println("Tipo de produto inválido.");
        }
    }

    private static void removerProduto() {
        System.out.println("\n--- Remover Produto ---");
        System.out.print("Digite o ID do produto a ser removido: ");
        try {
            int idRemover = scanner.nextInt();
            estoque.removerProduto(idRemover); // Sinaliza e lança ProdutoNaoEncontradoException
        } catch (InputMismatchException e) {
            System.err.println("Erro: ID inválido. Digite um número inteiro.");
            scanner.next(); // Limpar o buffer
        } catch (ProdutoNaoEncontradoException e) {
            System.err.println("Erro: " + e.getMessage()); // Trata a exceção específica
        }
    }

    private static void buscarProduto() {
        System.out.println("\n--- Buscar Produto ---");
        System.out.print("Digite o ID do produto a ser buscado: ");
        try {
            int idBuscar = scanner.nextInt();
            Produto produtoEncontrado = estoque.buscarProduto(idBuscar); // Sinaliza e lança ProdutoNaoEncontradoException
            System.out.println("Produto encontrado: " + produtoEncontrado + " - " + produtoEncontrado.obterInformacoesAdicionais());
        } catch (InputMismatchException e) {
            System.err.println("Erro: ID inválido. Digite um número inteiro.");
            scanner.next(); // Limpar o buffer
        } catch (ProdutoNaoEncontradoException e) {
            System.err.println("Erro: " + e.getMessage()); // Trata a exceção específica
        }
    }

    private static void atualizarProduto() {
        System.out.println("\n--- Atualizar Produto ---");
        System.out.print("Digite o ID do produto a ser atualizado: ");
        try {
            int idAtualizar = scanner.nextInt();
            Produto produtoExistente = estoque.buscarProduto(idAtualizar); // Sinaliza e lança ProdutoNaoEncontradoException
            scanner.nextLine(); // Consumir a quebra de linha

            System.out.print("Novo nome (" + produtoExistente.getNome() + "): ");
            String novoNome = scanner.nextLine();
            System.out.print("Novo preço (" + produtoExistente.getPreco() + "): ");
            double novoPreco = scanner.nextDouble();
            System.out.print("Nova quantidade em estoque (" + produtoExistente.getQuantidadeEstoque() + "): ");
            int novaQuantidade = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            Produto produtoAtualizado;
            if (produtoExistente instanceof Suplemento) {
                System.out.print("Novo sabor (" + ((Suplemento) produtoExistente).getSabor() + "): ");
                String novoSabor = scanner.nextLine();
                System.out.print("Novo tipo (" + ((Suplemento) produtoExistente).getTipo() + "): ");
                String novoTipo = scanner.nextLine();
                produtoAtualizado = new Suplemento(idAtualizar, novoNome, novoPreco, novaQuantidade, novoSabor, novoTipo);
            } else if (produtoExistente instanceof Acessorio) {
                System.out.print("Novo material (" + ((Acessorio) produtoExistente).getMaterial() + "): ");
                String novoMaterial = scanner.nextLine();
                System.out.print("Nova utilidade (" + ((Acessorio) produtoExistente).getUtilidade() + "): ");
                String novaUtilidade = scanner.nextLine();
                produtoAtualizado = new Acessorio(idAtualizar, novoNome, novoPreco, novaQuantidade, novoMaterial, novaUtilidade);
            } else {
                produtoAtualizado = new Produto(idAtualizar, novoNome, novoPreco, novaQuantidade);
            }
            estoque.atualizarProduto(produtoAtualizado); // Sinaliza e lança ProdutoNaoEncontradoException
        } catch (InputMismatchException e) {
            System.err.println("Erro: Entrada inválida. Digite um número.");
            scanner.next(); // Limpar o buffer
        } catch (ProdutoNaoEncontradoException e) {
            System.err.println("Erro: " + e.getMessage()); // Trata a exceção específica
        }
    }

    private static void listarProdutos() {
        estoque.listarProdutos();
    }
}