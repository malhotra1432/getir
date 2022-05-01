package com.getir.readingisgood.domain.customer.core.value;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class Address {
  @NonNull String street;
  @NonNull String houseNumber;
  @NonNull String zip;
  @NonNull String city;
  @NonNull String country;
}
