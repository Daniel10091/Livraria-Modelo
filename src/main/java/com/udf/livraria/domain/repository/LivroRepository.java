package com.udf.livraria.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udf.livraria.domain.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {
  
}
