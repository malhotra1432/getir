package com.getir.readingisgood.domain.book.domain.core.value;

import lombok.NonNull;
import lombok.Value;

@Value
public class Name {
  @NonNull String value;

  public static Name create(String name) {
    return new Name(name);
  }
}
