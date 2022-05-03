package com.getir.readingisgood.domain.order.domain.exception;

public class OrderException extends RuntimeException {

  public OrderException(String message) {
    this(message, null);
  }

  public OrderException(String message, Throwable throwable) {
    super(message, throwable);
  }
}
