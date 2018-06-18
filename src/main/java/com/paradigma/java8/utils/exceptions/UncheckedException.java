package com.paradigma.java8.utils.exceptions;

public class UncheckedException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public UncheckedException(Throwable cause) {
    super(cause);
  }
}
