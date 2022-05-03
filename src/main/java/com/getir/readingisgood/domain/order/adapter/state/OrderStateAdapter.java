package com.getir.readingisgood.domain.order.adapter.state;

import com.getir.readingisgood.domain.book.domain.core.value.BookId;
import com.getir.readingisgood.domain.book.domain.core.value.Name;
import com.getir.readingisgood.domain.customer.core.value.Email;
import com.getir.readingisgood.domain.order.adapter.entity.OrderStateEntity;
import com.getir.readingisgood.domain.order.domain.core.OrderState;
import com.getir.readingisgood.domain.order.domain.core.value.OrderId;
import java.time.Instant;
import org.springframework.stereotype.Component;

@Component
public class OrderStateAdapter {

  public OrderState decode(OrderStateEntity entity) {
    return OrderState.builder()
        .id(OrderId.create(entity.getId()))
        .customerEmail(Email.create(entity.getCustomerEmail()))
        .bookId(BookId.create(entity.getBookId()))
        .bookName(Name.create(entity.getBookName()))
        .bookPrice(entity.getBookPrice())
        .createdAt(entity.getCreatedAt())
        .build();
  }

  public OrderStateEntity encode(OrderState orderState) {
    return OrderStateEntity.builder()
        .customerEmail(orderState.getCustomerEmail().getValue())
        .bookId(orderState.getBookId().getValue())
        .bookName(orderState.getBookName().getValue())
        .bookPrice(orderState.getBookPrice())
        .createdAt(Instant.now())
        .build();
  }
}
