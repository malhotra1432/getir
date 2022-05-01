package com.getir.readingisgood.domain.customer.core;

import com.getir.readingisgood.domain.customer.command.CreateCustomer;
import lombok.Getter;
import lombok.NonNull;

public class Customer {
  @Getter private final CustomerState state;

  public Customer(CustomerState customerState) {
    this.state = customerState;
  }

  public static Customer create(@NonNull CreateCustomer createCustomer) {
    return create(createCustomer.toCustomerState());
  }

  public static Customer create(@NonNull CustomerState customerState) {
    return new Customer(customerState);
  }
}
