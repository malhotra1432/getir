package com.getir.readingisgood.domain.order.domain.core;

import com.getir.readingisgood.domain.book.domain.core.value.BookId;
import com.getir.readingisgood.domain.book.domain.core.value.Name;
import com.getir.readingisgood.domain.customer.core.value.Email;
import com.getir.readingisgood.domain.order.domain.core.value.OrderId;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@Data
public class OrderState {
  private OrderId orderId;
  @NonNull Email customerEmail;
  BookId bookId;
  @NonNull Name bookName;
  BigDecimal bookPrice;
  private Instant createdAt;
}
