package com.getir.readingisgood.util;

import com.getir.readingisgood.domain.book.adapter.repository.jpa.BookStateJPARepository;
import com.getir.readingisgood.domain.book.domain.command.CreateBook;
import com.getir.readingisgood.domain.book.domain.core.value.Name;
import com.getir.readingisgood.domain.customer.adapter.repository.jpa.CustomerJpaRepository;
import com.getir.readingisgood.domain.customer.command.CreateCustomer;
import com.getir.readingisgood.domain.customer.core.CustomerState;
import com.getir.readingisgood.domain.customer.core.value.*;
import com.getir.readingisgood.domain.order.adapter.repository.jpa.OrderStateJPARepository;
import com.getir.readingisgood.domain.order.domain.command.CreateOrder;
import com.github.javafaker.Faker;
import java.math.BigDecimal;
import java.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;

public class TestDataBuilder {
  private static final Randomizer randomizer = Randomizer.create();
  private static final Faker faker = Randomizer.create().getFaker();
  @Autowired private CustomerJpaRepository customerJpaRepository;
  @Autowired private BookStateJPARepository bookStateJPARepository;
  @Autowired private OrderStateJPARepository orderStateJPARepository;

  public static CustomerState.CustomerStateBuilder randomCustomerStateBuilder() {
    return CustomerState.builder()
        .firstName(FirstName.create(faker.funnyName().name()))
        .lastName(LastName.create(faker.funnyName().name()))
        .email(Email.create(faker.name().firstName() + "@gmail.com"))
        .contactNumber(ContactNumber.create(faker.phoneNumber().phoneNumber()))
        .creationDate(Instant.now())
        .address(addressBuilder());
  }

  public static Address addressBuilder() {
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
        .email(Email.create(faker.name().firstName() + "@gmail.com"))
        .contactNumber(ContactNumber.create(faker.phoneNumber().phoneNumber()))
        .address(addressBuilder())
        .password(Password.create("123456"));
  }

  public static CreateBook.CreateBookBuilder randomCreateBookBuilder() {
    return CreateBook.builder()
        .name(Name.create(faker.book().title()))
        .price(BigDecimal.valueOf(10));
  }

  public static CreateOrder.CreateOrderBuilder randomCreateOrderBuilder() {
    return CreateOrder.builder()
        .customerEmail(Email.create(faker.name().firstName() + "@gmail.com"))
        .bookName(Name.create(faker.book().title()));
  }

  public static Email randomEmail() {
    return Email.create(faker.name().firstName() + "@gmail.com");
  }
}
