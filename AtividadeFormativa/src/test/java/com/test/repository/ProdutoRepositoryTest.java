package com.test.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.test.entity.Produto;

@DataJpaTest
class ProdutoRepositoryTest {

	@Autowired
	private ProdutoRepository ProdutoRepository;


	@DisplayName("testando o save")
	@Test
	void test() {

		Produto Produto1 = new Produto(null, "sabonete",
				"dove",
				20);

		Produto saveProduto = ProdutoRepository.save(Produto1);

		assertNotNull(saveProduto);
		assertTrue(saveProduto.getId() > 0);
	}

	@DisplayName("Testando get para todos os Produtos")
	@Test
	void testGetAllRepsitory() {
		Produto Produto1 = new Produto (null, "sabonete",
				"dove",
				20);
		Produto Produto2 = new Produto (null, "pasta",
				"colgate",
				12);
		ProdutoRepository.save(Produto1);
		ProdutoRepository.save(Produto2);

		List<Produto> ProdutoList = ProdutoRepository.findAll();

		assertNotNull(ProdutoList);
		assertEquals(2, ProdutoList.size());
	}
	@DisplayName("Testando Get By ID")
	@Test
	void testGetById() {
		Produto Produto1 = new Produto (null, "sabonete",
				"dove",
				20);
		ProdutoRepository.save(Produto1);

		Produto saveProduto = ProdutoRepository.findById(Produto1.getId()).get();

		assertNotNull(saveProduto);
		assertEquals(Produto1.getId(), saveProduto.getId());
	}
	@DisplayName("Testando o Update")
	@Test
	void testUpdateProduto() {
		Produto Produto1 = new Produto (null, "sabonete",
				"dove",
				20);
		ProdutoRepository.save(Produto1);

		Produto saveProduto = ProdutoRepository.findById(Produto1.getId()).get();
		Produto1.setNome("arboleda");
		Produto1.setDescricao("arboleda zaga");

		Produto updateProduto = ProdutoRepository.save(saveProduto);

		assertNotNull(updateProduto);
		assertEquals("arboleda", updateProduto.getNome());
		assertEquals("arboleda zaga", updateProduto.getDescricao());
	}
	@DisplayName("testando o delete")
	@Test
	void testDeleteProduto() {
		Produto Produto1 = new Produto (null, "sabonete",
				"dove",
				20);
		ProdutoRepository.save(Produto1);

		ProdutoRepository.deleteById(Produto1.getId());
		Optional <Produto> ProdutoOptional = ProdutoRepository.findById(Produto1.getId());

		assertTrue(ProdutoOptional.isEmpty());
	}
}