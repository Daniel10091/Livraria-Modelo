package com.udf.livraria.domain.exception;

public class LivroAlreadyExistException extends RuntimeException {

  /**
   * @param message
   */
  public LivroAlreadyExistException(String message) {
    super(message);
  }

  /**
   * @param message
   * @param cause
   */
  public LivroAlreadyExistException(String message, Throwable cause) {
    super(message, cause);
  }

}
