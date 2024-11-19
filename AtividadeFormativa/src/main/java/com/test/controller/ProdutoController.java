package com.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.entity.Produto;
import com.test.service.ProdutoService;


@RequestMapping("/produto")
@RestController
public class ProdutoController {
	private final ProdutoService produtoService;
	@Autowired
	public ProdutoController(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> getProdutoById(@PathVariable Long id) {
		Produto produto = produtoService.getProdutoById(id);
		if (produto != null) {
			return ResponseEntity.ok(produto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	public ResponseEntity<List<Produto>> getAllProduto() {
		List<Produto> produto = produtoService.getAllProduto();
		return ResponseEntity.ok(produto);
	}
	//@Query
	//@GetMapping("/data/{data}")
	//@Operation(summary = "Aprensenta a data da consulta")
	//public ResponseEntity<List<Consulta>> getConsultaPorData(@PathVariable String data){
	//List<Consulta> consultas = consultaService.getConsultaPorData(data);
	//return ResponseEntity.ok(consultas);
	//}
	//@Query
	//@GetMapping("/hora/{hora}")
	//@Operation(summary = "Aprensenta a hora consulta")
	//public List<Consulta> findConsultasPorHora(@PathVariable String hora){
	//return consultaService.findByHora(hora);
	//}

	//@Query
	//@GetMapping("/descricao/{descricao}")
	//@Operation(summary = "Aprensenta a descricao consulta")
	//public List<Consulta> findPorDescricao(@PathVariable String descricao){
	//return consultaService.findByDescricao(descricao);
	//}


	@PostMapping("/")
	public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto) {
		Produto criarProduto = produtoService.salvarProduto(produto);
		return ResponseEntity.status(HttpStatus.CREATED).body(criarProduto);
	}


	@PutMapping("/{id}")
	public ResponseEntity<Produto> updateProduto(@PathVariable Long id,@RequestBody Produto produto) {
		Produto updatedProduto = produtoService.updateProduto(id, produto);
		if (updatedProduto != null) {
			return ResponseEntity.ok(updatedProduto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProduto(@PathVariable Long id) {
		boolean deleted = produtoService.deleteProduto(id);
		if (deleted) {
			return ResponseEntity.ok().body("O produto foi excluído com sucesso.");
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}