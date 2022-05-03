package com.getir.readingisgood.domain.order.domain.command;

import com.getir.readingisgood.domain.book.domain.core.value.Name;
import com.getir.readingisgood.domain.customer.core.value.Email;
import com.getir.readingisgood.domain.order.domain.core.OrderState;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class CreateOrder {
  @NonNull Email customerEmail;
  @NonNull Name bookName;

  public OrderState toOrderState() {
    return OrderState.builder().customerEmail(customerEmail).bookName(bookName).build();
  }
}
