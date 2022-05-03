package com.getir.readingisgood.domain.order.domain.core.value;

import lombok.NonNull;
import lombok.Value;

@Value
public class OrderId {
  @NonNull Long value;

  public static OrderId create(Long orderId) {
    return new OrderId(orderId);
  }
}
