package com.udf.livraria.domain.exception;

public class LivroNotFoundException extends RuntimeException {

  /**
   * @param message
   */
  public LivroNotFoundException(String message) {
    super(message);
  }

  /**
   * @param message
   * @param cause
   */
  public LivroNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

}
