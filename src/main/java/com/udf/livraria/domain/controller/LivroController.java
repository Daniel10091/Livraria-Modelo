package com.udf.livraria.domain.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.udf.livraria.domain.exception.LivroNotFoundException;
import com.udf.livraria.domain.model.Livro;
import com.udf.livraria.domain.service.LivroSevice;


@RestController
@RequestMapping("/livraria")
public class LivroController {
  
  private final LivroSevice service;

  public LivroController(LivroSevice service) {
    this.service = service;
  }

  @GetMapping(value="/listar")
  public ResponseEntity<List<Livro>> listarTodos() {
    // Busca todos os livros
    List<Livro> livros = service.listarTodos();
    // Retorna os livros (ok = 200)
    return ResponseEntity.ok(livros);
  }
  
  @GetMapping(value="/buscar")
  public ResponseEntity<Livro> buscarPorId(@RequestParam(value="id") Long id) {
    // Busca o livro pelo id
    Livro livro = service.buscarPorId(id);
    // Retorna o livro (ok = 200)
    return ResponseEntity.ok(livro);
  }

  @PostMapping("/cadastrar")
  public ResponseEntity<Livro> cadastrar(@RequestBody Livro livro) {
    // Salva o livro
    Livro livroSalvo = service.cadastrar(livro);
    // Retorna o livro (ok = 200)
    return ResponseEntity.ok(livroSalvo);
  }

  @PostMapping("/atualizar")
  public ResponseEntity<Livro> atualizar(@RequestBody Livro livro) {
    // Atualiza o livro
    Livro livroAtualizado = service.atualizar(livro);
    // Retorna o livro (ok = 200)
    return ResponseEntity.ok(livroAtualizado);
  }

  @DeleteMapping("/deletar")
  public ResponseEntity<Livro> deletar(@RequestParam(value="id") Long id) throws LivroNotFoundException {
    // Deleta o livro
    service.deletar(id);
    // Retorna OK (ok = 200)
    return ResponseEntity.ok().build();
  }

}
