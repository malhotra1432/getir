package com.getir.readingisgood.domain.order.domain.exception;

import com.getir.readingisgood.domain.order.domain.core.value.OrderId;
import lombok.Getter;

public class UnknownOrderIdException extends OrderException {
  @Getter private final OrderId orderId;

  public UnknownOrderIdException(OrderId orderId) {
    this(orderId, null);
  }

  public UnknownOrderIdException(OrderId orderId, Throwable throwable) {
    super("Order with id " + orderId.getValue() + " does not exist.", throwable);
    this.orderId = orderId;
  }
}
