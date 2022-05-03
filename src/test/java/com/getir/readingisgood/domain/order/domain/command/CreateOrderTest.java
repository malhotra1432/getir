package com.getir.readingisgood.domain.order.domain.command;

import static org.assertj.core.api.Assertions.assertThat;

import com.getir.readingisgood.domain.order.domain.core.OrderState;
import com.getir.readingisgood.util.TestDataBuilder;
import org.junit.jupiter.api.Test;

class CreateOrderTest {
  @Test
  void shouldCreateOrderStateFromCommand() {
    CreateOrder createOrder = TestDataBuilder.randomCreateOrderBuilder().build();
    assertThat(createOrder.toOrderState()).isInstanceOf(OrderState.class);
  }
}
