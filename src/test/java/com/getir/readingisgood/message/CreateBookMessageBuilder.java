package com.getir.readingisgood.message;

import com.getir.readingisgood.util.Randomizer;
import com.github.javafaker.Faker;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;

@AllArgsConstructor
@NoArgsConstructor
public class CreateBookMessageBuilder {
  private final Randomizer randomizer = Randomizer.create();
  private static final Faker faker = Randomizer.create().getFaker();
  @With private String name = faker.book().title();

  public static CreateBookMessageBuilder create() {
    return new CreateBookMessageBuilder();
  }

  public CreateBookMessage buildCreateBookMessage() {
    return CreateBookMessage.builder().name(name).price(BigDecimal.valueOf(20)).build();
  }
}
