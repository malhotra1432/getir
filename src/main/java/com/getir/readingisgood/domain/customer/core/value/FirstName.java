package com.getir.readingisgood.domain.customer.core.value;

import lombok.NonNull;
import lombok.Value;

@Value
public class FirstName {
  @NonNull String value;

  public static FirstName create(String firstName) {
    return new FirstName(firstName);
  }
}
