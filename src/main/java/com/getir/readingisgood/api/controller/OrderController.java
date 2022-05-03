package com.getir.readingisgood.api.controller;

import com.getir.readingisgood.domain.order.domain.command.CreateOrder;
import com.getir.readingisgood.domain.order.domain.service.OrderService;
import com.getir.readingisgood.message.CreateOrderMessage;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1/order")
public class OrderController {
  private final OrderService orderService;

  @Autowired
  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping("/create")
  @ResponseStatus(value = HttpStatus.ACCEPTED)
  public ResponseEntity<String> create(@Valid @RequestBody CreateOrderMessage createOrderMessage) {
    log.info("Create order with name: {}", createOrderMessage.getBookName());
    CreateOrder createOrder = CreateOrderMessage.toCreateOrderCommand(createOrderMessage);
    orderService.create(createOrder);
    return ResponseEntity.accepted().body("Order created successfully!");
  }
}
