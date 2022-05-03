package com.getir.readingisgood.domain.order.domain.core;

import com.getir.readingisgood.domain.order.domain.command.CreateOrder;
import lombok.Getter;
import lombok.NonNull;

public class Order {
  @Getter private final OrderState state;

  public Order(OrderState orderState) {
    this.state = orderState;
  }

  public static Order create(@NonNull CreateOrder createOrder) {
    return create(createOrder.toOrderState());
  }

  public static Order create(@NonNull OrderState orderState) {
    return new Order(orderState);
  }
}
