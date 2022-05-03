package com.getir.readingisgood.api.controller;

import com.getir.readingisgood.api.controller.model.CustomerAllOrderResponse;
import com.getir.readingisgood.api.controller.model.OrderByIdResponse;
import com.getir.readingisgood.domain.customer.core.value.Email;
import com.getir.readingisgood.domain.order.domain.command.CreateOrder;
import com.getir.readingisgood.domain.order.domain.core.value.OrderId;
import com.getir.readingisgood.domain.order.domain.exception.UnknownOrderIdException;
import com.getir.readingisgood.domain.order.domain.service.OrderService;
import com.getir.readingisgood.message.CreateOrderMessage;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
    try {
      CreateOrder createOrder = CreateOrderMessage.toCreateOrderCommand(createOrderMessage);
      orderService.create(createOrder);
      return ResponseEntity.accepted().body("Order created successfully!");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("Order not placed!" + e);
    }
  }

  @GetMapping("/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  public ResponseEntity<OrderByIdResponse> getOrderById(@Valid @PathVariable Long id)
      throws UnknownOrderIdException {
    log.info("Get order by id: {}", id);
    var order = orderService.getOrderById(OrderId.create(id)).getState();
    return ResponseEntity.ok()
        .body(
            OrderByIdResponse.builder()
                .id(order.getId().getValue())
                .bookId(order.getBookId().getValue())
                .bookName(order.getBookName().getValue())
                .bookPrice(order.getBookPrice())
                .createdAt(order.getCreatedAt())
                .customerEmail(order.getCustomerEmail().getValue())
                .build());
  }

  @GetMapping("/email/{email}")
  public ResponseEntity<CustomerAllOrderResponse> getOrderByPage(
      Pageable pageable, @PathVariable String email) {
    return new ResponseEntity<>(
        orderService.getOrderByPage(pageable, Email.create(email)), HttpStatus.OK);
  }
}
