package com.getir.readingisgood.domain.customer.exception;

import com.getir.readingisgood.domain.customer.core.value.Email;
import lombok.Getter;

public class UnknownCustomerEmailException extends CustomerException {
  @Getter private final Email email;

  public UnknownCustomerEmailException(Email email) {
    this(email, null);
  }

  public UnknownCustomerEmailException(Email email, Throwable throwable) {
    super(email + " does not exist.", throwable);
    this.email = email;
  }
}
