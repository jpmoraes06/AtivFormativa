package com.test.entity;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProdutoTest {
	private Produto produto;
	@BeforeEach
	void setUp() {
		produto = new Produto(1L, "joao", "joao jaca", 20000);
	}

	@Test
	@DisplayName("testando o getter e setter do campo ID")
	void testId() {
		produto.setId(2L);
		//Assert
		assertEquals(2L, produto.getId());
	}
	@Test
	@DisplayName("testando o getter e setter do campo nome")
	void testNome() {
		produto.setNome("rabinha");
		assertEquals("rabinha", produto.getNome());
	}
	@Test
	@DisplayName("testando o getter e setter do campo descricao")
	void testDescricao() {
		produto.setDescricao("rabinha dahora");
		assertEquals("rabinha dahora", produto.getDescricao());
	}
	@Test
	@DisplayName("testando o getter e setter do campo preco")
	void testPreco() {
		produto.setPreco(70000);
		assertEquals("1 acarajé", produto.getPreco());
	}
	@Test
	@DisplayName("Testando o construtor com todos os argumentos")
	void testConstrutorAll() {
		Produto novoProduto = new Produto(3L, "vitinha", "doguinho", 0.1);
		assertAll("novoProduto",
				()-> assertEquals(3L, novoProduto.getId()),
				()-> assertEquals("vitinha", novoProduto.getNome()),
				()-> assertEquals("doguinha", novoProduto.getDescricao()),
				()-> assertEquals(0,1, novoProduto.getPreco()));
	}
}
