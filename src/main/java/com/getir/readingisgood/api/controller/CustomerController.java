package com.getir.readingisgood.api.controller;

import com.getir.readingisgood.domain.customer.command.CreateCustomer;
import com.getir.readingisgood.domain.customer.service.CustomerService;
import com.getir.readingisgood.message.CreateCustomerMessage;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1/customer")
public class CustomerController {
  private final CustomerService customerService;

  @Autowired
  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @PostMapping("/register")
  @ResponseStatus(value = HttpStatus.ACCEPTED)
  public ResponseEntity<String> create(@Valid @RequestBody CreateCustomerMessage customerMessage) {
    log.info("Create customer with email: {}", customerMessage.getEmail());
    CreateCustomer createCustomer = CreateCustomerMessage.toCreateCustomerCommand(customerMessage);
    customerService.create(createCustomer);
    return ResponseEntity.accepted().body("Customer registered successfully!");
  }
}
