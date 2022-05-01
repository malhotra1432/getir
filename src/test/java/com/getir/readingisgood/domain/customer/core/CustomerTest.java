package com.getir.readingisgood.domain.customer.core;

import static org.assertj.core.api.Assertions.assertThat;

import com.getir.readingisgood.domain.customer.command.CreateCustomer;
import com.getir.readingisgood.util.TestDataBuilder;
import org.junit.jupiter.api.Test;

class CustomerTest {
  @Test
  void shouldCreateCustomerDomainFromCommand() {
    CreateCustomer createCustomer = TestDataBuilder.randomCreateCustomerBuilder().build();
    var customerState = createCustomer.toCustomerState();
    assertThat(Customer.create(customerState)).isInstanceOf(Customer.class);
  }
}
