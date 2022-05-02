package com.getir.readingisgood.domain.book.domain.exception;

public class BookException extends RuntimeException {

  public BookException(String message) {
    this(message, null);
  }

  public BookException(String message, Throwable throwable) {
    super(message, throwable);
  }
}
