package com.getir.readingisgood.api.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.getir.readingisgood.domain.customer.core.value.Email;
import com.getir.readingisgood.domain.order.domain.command.CreateOrder;
import com.getir.readingisgood.domain.order.domain.service.OrderService;
import com.getir.readingisgood.message.CreateOrderMessage;
import com.getir.readingisgood.message.CreateOrderMessageBuilder;
import com.getir.readingisgood.util.Randomizer;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

class OrderControllerTest {
  private final OrderService orderService = mock(OrderService.class);
  private static final Faker faker = Randomizer.create().getFaker();
  private final Email email = Email.create(faker.name().firstName() + "@gmail.com");
  private final CreateOrderMessageBuilder createOrderMessageBuilder =
      CreateOrderMessageBuilder.create().withCustomerEmail(email.getValue());

  @Test
  void shouldCreateOrder() {
    var createOrderMessage = createOrderMessageBuilder.buildCreateOrderMessage();
    OrderController orderController = new OrderController(orderService);

    orderController.create(createOrderMessage);

    verify(orderService).create(buildCreateOrderCommand(createOrderMessage));
  }

  private CreateOrder buildCreateOrderCommand(CreateOrderMessage createOrderMessage) {
    return CreateOrderMessage.toCreateOrderCommand(createOrderMessage);
  }

  @Test
  void shouldGetOrderByPage() {
    OrderController orderController = new OrderController(orderService);
    Pageable pageable = PageRequest.of(0, 2);

    orderController.getOrderByPage(pageable, email.getValue());

    verify(orderService).getOrderByPage(pageable, email);
  }

  @Test
  void shouldGetMonthlyStats() {
    OrderController orderController = new OrderController(orderService);

    orderController.getCustomerMonthlyStats(email.getValue());

    verify(orderService).getCustomerMonthlyStats(email);
  }
}
