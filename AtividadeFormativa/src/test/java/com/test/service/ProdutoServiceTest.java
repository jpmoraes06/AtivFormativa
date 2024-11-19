package com.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.entity.Produto;
import com.test.repository.ProdutoRepository;

class ProdutoServiceTest {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ProdutoRepository produtoRepository;

    @BeforeEach
    void setUp() {
        produtoRepository.deleteAll();
    }

    @DisplayName("Testando salvar Produto")
    @Test
    void testSalvarProduto() {
        Produto produto = new Produto(null, "gp", "mc gp", 1000.00);

        Produto resultado = produtoService.salvarProduto(produto);

        assertNotNull(resultado);
        assertEquals("Jp", resultado.getNome());
        assertTrue(resultado.getId() > 0);
    }

    @DisplayName("Testando listar Produtos")
    @Test
    void testListarTodos() {
        Produto produto1 = new Produto(null, "gp", "mc gp", 1000.00);
        Produto produto2 = new Produto(null, "glife", "glifee", 5.00);
        produtoService.salvarProduto(produto1);
        produtoService.salvarProduto(produto2);

        List<Produto> resultado = produtoService.listarTodos();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
    }

    @DisplayName("Testando atualizar Produto")
    @Test
    void testAtualizarProduto() {
        Produto produto = new Produto(null, "gp", "mc gp", 1000.00);
        Produto salvo = produtoService.salvarProduto(produto);
        salvo.setNome("nicole");
        salvo.setDescricao("nicole gp");

        Produto atualizado = produtoService.salvarProduto(salvo);

        assertNotNull(atualizado);
        assertEquals("nicole", atualizado.getNome());
        assertEquals("nicole gp", atualizado.getDescricao());
    }

    @DisplayName("testando deletar produto")
    @Test
    void testDeletarProduto() {
        Produto produto = new Produto(null, "gp", "mc gp", 1000.00);
        Produto salvo = produtoService.salvarProduto(produto);
        produtoService.deleteProduto(salvo.getId());
        Optional<Produto> resultado = produtoService.buscarPorId(salvo.getId());

        assertTrue(resultado.isEmpty());
    }
}
