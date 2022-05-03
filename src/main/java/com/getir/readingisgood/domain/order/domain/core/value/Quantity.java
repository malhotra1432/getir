package com.getir.readingisgood.domain.order.domain.core.value;

import lombok.NonNull;
import lombok.Value;

@Value
public class Quantity {
  @NonNull Integer value;

  public static Quantity create(Integer quantity) {
    return new Quantity(quantity);
  }
}
