package com.getir.readingisgood.message;

import com.getir.readingisgood.domain.book.domain.core.value.Name;
import com.getir.readingisgood.domain.customer.core.value.Email;
import com.getir.readingisgood.domain.order.domain.command.CreateOrder;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderMessage {

  @NotEmpty(message = "customerEmail is mandatory")
  private String customerEmail;

  @NotEmpty(message = "bookName is mandatory")
  private String bookName;

  public static CreateOrder toCreateOrderCommand(CreateOrderMessage createBookMessage) {
    return CreateOrder.builder()
        .customerEmail(Email.create(createBookMessage.getCustomerEmail()))
        .bookName(Name.create(createBookMessage.getBookName()))
        .build();
  }
}
