package com.getir.readingisgood.api.controller.model;

import com.getir.readingisgood.domain.order.domain.core.OrderState;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class OrderByIdResponse {
  Long id;
  String customerEmail;
  Long bookId;
  String bookName;
  BigDecimal bookPrice;
  Instant createdAt;

  public static OrderByIdResponse toOrderByIdResponse(OrderState order) {
    return OrderByIdResponse.builder()
        .id(order.getId().getValue())
        .bookId(order.getBookId().getValue())
        .bookName(order.getBookName().getValue())
        .bookPrice(order.getBookPrice())
        .createdAt(order.getCreatedAt())
        .customerEmail(order.getCustomerEmail().getValue())
        .build();
  }
}
