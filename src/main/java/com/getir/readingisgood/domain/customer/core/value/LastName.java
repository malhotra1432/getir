package com.getir.readingisgood.domain.customer.core.value;

import lombok.NonNull;
import lombok.Value;

@Value
public class LastName {
  @NonNull String value;

  public static LastName create(String lastName) {
    return new LastName(lastName);
  }
}
