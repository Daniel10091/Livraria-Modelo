package com.udf.livraria.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.udf.livraria.domain.exception.LivroNotFoundException;
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
    Livro livroSalvo = null;
    Livro livroAtualizado = null;
    // Verifica se o livro a ser salvo contém id
    if (livro.getId() == null) {
      // Busca o livro no banco de dados
      livroAtualizado = repository.findById(livro.getId()).get();
      // Verifica se o livro foi encontrado
      if (livroAtualizado != null) {
        // Atualiza o livro
        livroAtualizado.setIsbn(livro.getIsbn());
        livroAtualizado.setTitulo(livro.getTitulo());
        livroAtualizado.setAutor(livro.getAutor());
        livroAtualizado.setEditora(livro.getEditora());
      } else {
        // Dispara uma excessão caso o livro não for encontrado
        throw new LivroNotFoundException("O livro com o id '" + livro.getId() + "' não foi encontrado");
      }
      // Salva o livro no banco de dados
      livroSalvo = repository.save(livroAtualizado);
    }
    // Retorna o livro salvo
    return livroSalvo;
  }

  // Deleta um livro do banco de dados
  public void deletar(Long id) {
    repository.deleteById(id);
  }

}
