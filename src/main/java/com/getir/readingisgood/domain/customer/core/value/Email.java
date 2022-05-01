package com.getir.readingisgood.domain.customer.core.value;

import lombok.NonNull;
import lombok.Value;

@Value
public class Email {
  @NonNull String value;

  public static Email create(String email) {
    return new Email(email);
  }
}
