package com.getir.readingisgood.domain.customer.core.value;

import lombok.NonNull;
import lombok.Value;

@Value
public class CustomerId {
  @NonNull Long value;

  public static CustomerId create(Long customerId) {
    return new CustomerId(customerId);
  }
}
