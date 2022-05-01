package com.getir.readingisgood.util;

import com.getir.readingisgood.domain.customer.command.CreateCustomer;
import com.getir.readingisgood.domain.customer.core.CustomerState;
import com.getir.readingisgood.domain.customer.core.value.*;
import com.github.javafaker.Faker;
import java.time.Instant;

public class TestDataBuilder {
  private static final Randomizer randomizer = Randomizer.create();
  private static final Faker faker = Randomizer.create().getFaker();

  public static CustomerState.CustomerStateBuilder randomCustomerStateBuilder() {
    return CustomerState.builder()
        .firstName(FirstName.create(faker.funnyName().name()))
        .lastName(LastName.create(faker.funnyName().name()))
        .email(Email.create(faker.funnyName().name() + "@gmail.com"))
        .contactNumber(ContactNumber.create(faker.phoneNumber().phoneNumber()))
        .creationDate(Instant.now())
        .address(addressBuilder());
  }

  private static Address addressBuilder() {
    return Address.builder()
        .city(faker.address().cityName())
        .houseNumber(faker.address().buildingNumber())
        .street(faker.address().streetName())
        .zip(faker.address().zipCode())
        .country(faker.country().name())
        .build();
  }

  public static CreateCustomer.CreateCustomerBuilder randomCreateCustomerBuilder() {
    return CreateCustomer.builder()
        .firstName(FirstName.create(faker.funnyName().name()))
        .lastName(LastName.create(faker.funnyName().name()))
        .email(Email.create(faker.funnyName().name() + "@gmail.com"))
        .contactNumber(ContactNumber.create(faker.phoneNumber().phoneNumber()))
        .address(addressBuilder())
        .password(Password.create("123456"));
  }

  public static Email randomEmail() {
    return Email.create(faker.funnyName().name() + "@gmail.com");
  }
}
