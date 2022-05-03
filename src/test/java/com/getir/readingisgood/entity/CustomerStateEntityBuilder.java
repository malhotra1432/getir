package com.getir.readingisgood.entity;

import com.getir.readingisgood.domain.customer.adapter.entity.CustomerStateEntity;
import com.getir.readingisgood.util.Randomizer;
import com.getir.readingisgood.util.TestDataBuilder;
import java.time.Instant;

public class CustomerStateEntityBuilder {
  static Randomizer randomizer = Randomizer.create();

  public static CustomerStateEntity.CustomerStateEntityBuilder randomCustomerStateEntityBuilder() {
    return CustomerStateEntity.builder()
        .id(Long.valueOf(randomizer.nextInt()))
        .firstName(randomizer.getFaker().name().firstName())
        .lastName(randomizer.getFaker().name().firstName())
        .contactNumber("9860758578")
        .password("111222")
        .address(TestDataBuilder.addressBuilder().toString())
        .createdAt(Instant.now());
  }
}
