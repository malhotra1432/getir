package com.getir.readingisgood.domain.customer.exception;

import com.getir.readingisgood.domain.customer.core.value.CustomerId;
import lombok.Getter;

public class UnknownCustomerIdException extends CustomerException {
  @Getter private final CustomerId customerId;

  public UnknownCustomerIdException(CustomerId customerId) {
    this(customerId, null);
  }

  public UnknownCustomerIdException(CustomerId customerId, Throwable throwable) {
    super(customerId + " does not exist.", throwable);
    this.customerId = customerId;
  }
}
