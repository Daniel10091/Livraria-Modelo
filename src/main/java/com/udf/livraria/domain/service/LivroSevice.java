package com.udf.livraria.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.udf.livraria.domain.exception.LivroAlreadyExistException;
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
    // Retorna a consulta do livro no banco de dados atravéz do id e, caso não encotre, dispara uma excessão
    return repository.findLivroById(id)
        .orElseThrow(() -> new LivroNotFoundException("O livro com o id '" + id + "' não foi encontrado"));
  }

  // Busca um livro pelo titulo
  public Livro filtraPorTitulo(String titulo) {
    // Retorna a consulta do livro no banco de dados atravéz do titulo e, caso não encotre, dispara uma excessão
    return repository.findLivroByTitulo(titulo)
        .orElseThrow(() -> new LivroNotFoundException("O livro com o titulo '" + titulo + "' não foi encontrado"));
  }

  // Cadastra um livro no banco de dados
  public Livro cadastrar(Livro livro) {
    // Busca o livro no banco de dados atravéz do titulo
    var livroExistente = repository.findByTitulo(livro.getTitulo());
    // Verifica se o livro já existe
    if (livroExistente != null) {
      // Dispara uma excessão caso o livro já exista
      throw new LivroAlreadyExistException("O livro com o titulo '" + livro.getTitulo() + "' já existe");
    }
    // Salva o livro no banco de dados
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
    // Busca o livro no banco de dados
    Livro livroADeletar = buscarPorId(id);
    // Verifica se o livro foi encontrado
    if (livroADeletar == null) {
      // Dispara uma excessão caso o livro não for encontrado
      throw new LivroNotFoundException("O livro com o id '" + id + "' não foi encontrado");
    }
    // Deleta o livro do banco de dados
    repository.deleteById(id);
  }

}
