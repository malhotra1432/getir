package com.getir.readingisgood.domain.customer.core.value;

import lombok.NonNull;
import lombok.Value;

@Value
public class ContactNumber {
  @NonNull String value;

  public static ContactNumber create(String contactNumber) {
    return new ContactNumber(contactNumber);
  }
}
