package com.getir.readingisgood.domain.customer.command;

import com.getir.readingisgood.domain.customer.core.CustomerState;
import com.getir.readingisgood.domain.customer.core.value.*;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class CreateCustomer {
  @NonNull FirstName firstName;
  @NonNull LastName lastName;
  @NonNull Email email;
  @NonNull ContactNumber contactNumber;
  @NonNull Address address;
  @NonNull Password password;

  public CustomerState toCustomerState() {
    return CustomerState.builder()
        .firstName(firstName)
        .lastName(lastName)
        .email(email)
        .contactNumber(contactNumber)
        .address(address)
        .password(password)
        .build();
  }
}
