package com.getir.readingisgood.message;

import com.getir.readingisgood.util.Randomizer;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;

@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderMessageBuilder {
  private final Randomizer randomizer = Randomizer.create();
  private static final Faker faker = Randomizer.create().getFaker();
  @With private String customerEmail = faker.name().firstName() + "@gmail.com";
  @With private String bookName = randomizer.uuid();

  public static CreateOrderMessageBuilder create() {
    return new CreateOrderMessageBuilder();
  }

  public CreateOrderMessage buildCreateOrderMessage() {
    return CreateOrderMessage.builder().customerEmail(customerEmail).bookName(bookName).build();
  }
}
