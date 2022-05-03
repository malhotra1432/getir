package com.getir.readingisgood.api.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.getir.readingisgood.domain.customer.command.CreateCustomer;
import com.getir.readingisgood.domain.customer.core.value.Email;
import com.getir.readingisgood.domain.customer.service.CustomerService;
import com.getir.readingisgood.message.CreateCustomerMessage;
import com.getir.readingisgood.message.CreateCustomerMessageBuilder;
import com.getir.readingisgood.util.Randomizer;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

class CustomerControllerTest {
  private final CustomerService customerService = mock(CustomerService.class);
  private static final Faker faker = Randomizer.create().getFaker();
  private final Email email = Email.create(faker.name().firstName() + "@gmail.com");
  private final CreateCustomerMessageBuilder createCustomerMessage =
      CreateCustomerMessageBuilder.create().withEmail(email.getValue());

  @Test
  void shouldCreateCustomer() {
    var customerMessage = createCustomerMessage.buildCreateCustomerMessage();
    CustomerController customerController = new CustomerController(customerService);

    customerController.create(customerMessage);

    verify(customerService).create(buildCreateCustomerCommand(customerMessage));
  }

  private CreateCustomer buildCreateCustomerCommand(CreateCustomerMessage customerMessage) {
    return CreateCustomerMessage.toCreateCustomerCommand(customerMessage);
  }
}
