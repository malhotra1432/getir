package com.getir.readingisgood.domain.customer.exception;

public class CustomerException extends RuntimeException {

  public CustomerException(String message) {
    this(message, null);
  }

  public CustomerException(String message, Throwable throwable) {
    super(message, throwable);
  }
}
