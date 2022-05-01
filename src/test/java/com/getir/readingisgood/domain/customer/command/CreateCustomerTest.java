package com.getir.readingisgood.domain.customer.command;

import static org.assertj.core.api.Assertions.assertThat;

import com.getir.readingisgood.domain.customer.core.CustomerState;
import com.getir.readingisgood.util.TestDataBuilder;
import org.junit.jupiter.api.Test;

class CreateCustomerTest {
  @Test
  void shouldCreateCustomerStateFromCommand() {
    CreateCustomer createCustomer = TestDataBuilder.randomCreateCustomerBuilder().build();
    assertThat(createCustomer.toCustomerState()).isInstanceOf(CustomerState.class);
  }
}
