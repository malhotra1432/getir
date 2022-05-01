package com.getir.readingisgood.message;

import com.getir.readingisgood.domain.customer.core.value.ContactNumber;
import com.getir.readingisgood.domain.customer.core.value.Password;
import com.getir.readingisgood.util.Randomizer;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;

@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerMessageBuilder {
  private final Randomizer randomizer = Randomizer.create();
  private static final Faker faker = Randomizer.create().getFaker();
  @With private String firstName = faker.name().firstName();
  @With private String lastName = faker.name().lastName();
  @With private String email = faker.name().firstName() + "@gmail.com";
  @With private AddressData address = randomAddress();
  @With private Password password = Password.create("123456");

  @With
  private ContactNumber contactNumber = ContactNumber.create(faker.phoneNumber().phoneNumber());

  public static CreateCustomerMessageBuilder create() {
    return new CreateCustomerMessageBuilder();
  }

  private AddressData randomAddress() {
    var address = randomizer.getFaker().address();
    return AddressData.builder()
        .street(address.streetName())
        .houseNumber(address.buildingNumber())
        .zip("123456")
        .city(address.city())
        .country(address.country())
        .build();
  }

  public CreateCustomerMessage buildCreateCustomerMessage() {
    return CreateCustomerMessage.builder()
        .firstName(firstName)
        .lastName(lastName)
        .email(email)
        .address(randomAddress())
        .contactNumber("98477644444")
        .password(password.getValue())
        .build();
  }
}
