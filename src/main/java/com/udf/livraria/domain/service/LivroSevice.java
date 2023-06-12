package com.udf.livraria.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.udf.livraria.domain.model.Livro;
import com.udf.livraria.domain.repository.LivroRepository;

@Service
public class LivroSevice {
  
  private final LivroRepository repository;

  public LivroSevice(LivroRepository repository) {
    this.repository = repository;
  }

  // Lista todos os livros cadastrados no banco de dados
  public List<Livro> listarTodos() {
    return repository.findAll();
  }

  // Busca um livro pelo id
  public Livro buscarPorId(Long id) {
    return repository.findById(id).get();
  }

  // Cadastra um livro no banco de dados
  public Livro cadastrar(Livro livro) {
    // Código...
    return repository.save(livro);
  }

  // Atualiza um livro no banco de dados
  public Livro atualizar(Livro livro) {
    // Código...
    return repository.save(livro);
  }

  // Deleta um livro do banco de dados
  public void deletar(Long id) {
    repository.deleteById(id);
  }

}
