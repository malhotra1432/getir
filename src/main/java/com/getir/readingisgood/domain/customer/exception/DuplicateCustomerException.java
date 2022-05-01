package com.getir.readingisgood.domain.customer.exception;

import com.getir.readingisgood.domain.customer.core.value.Email;
import lombok.Getter;

public class DuplicateCustomerException extends CustomerException {
  @Getter private final Email email;

  public DuplicateCustomerException(Email email) {
    this(email, null);
  }

  public DuplicateCustomerException(Email email, Throwable throwable) {
    super(
        "Create customer requested for already existing one with email " + email.getValue(),
        throwable);
    this.email = email;
  }
}
