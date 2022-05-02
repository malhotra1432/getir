package com.getir.readingisgood.message;

import com.getir.readingisgood.domain.book.domain.command.CreateBook;
import com.getir.readingisgood.domain.book.domain.core.value.Name;
import java.math.BigDecimal;
import javax.validation.constraints.NotEmpty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateBookMessage {

  @NotEmpty(message = "name is mandatory")
  private String name;

  @NonNull private BigDecimal price;

  public static CreateBook toCreateBookCommand(CreateBookMessage createBookMessage) {
    return CreateBook.builder()
        .name(Name.create(createBookMessage.getName()))
        .price(createBookMessage.getPrice())
        .build();
  }
}
