package com.getir.readingisgood.domain.customer.core.value;

import lombok.NonNull;
import lombok.Value;

@Value
public class Password {
  @NonNull String value;

  public static Password create(String password) {
    return new Password(password);
  }
}
