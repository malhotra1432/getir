package com.getir.readingisgood.api.controller.model;

import java.math.BigDecimal;
import java.time.Instant;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class CustomerOrderData {
  Long id;
  String customerEmail;
  Long bookId;
  String bookName;
  BigDecimal bookPrice;
  Instant createdAt;
}
