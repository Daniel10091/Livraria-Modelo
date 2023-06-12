package com.udf.livraria.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "livro")
public class Livro {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "isbn", nullable = false)
  private String isbn;

  @Column(name = "titulo", nullable = false)
  private String titulo;

  @Column(name = "autor", nullable = false)
  private String autor;

  @Column(name = "editora", nullable = false)
  private String editora;

}
