package com.getir.readingisgood.domain.customer.core;

import com.getir.readingisgood.domain.customer.core.value.*;
import java.time.Instant;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@Data
public class CustomerState {
  private CustomerId customerId;
  @NonNull private FirstName firstName;
  @NonNull private LastName lastName;
  @NonNull private Email email;
  @NonNull private ContactNumber contactNumber;
  @NonNull private Address address;
  private Instant creationDate;
  private Password password;
}
