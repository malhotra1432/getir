package com.getir.readingisgood.domain.customer.service;

import com.getir.readingisgood.domain.customer.command.CreateCustomer;
import com.getir.readingisgood.domain.customer.core.Customer;
import com.getir.readingisgood.domain.customer.core.value.Email;
import com.getir.readingisgood.domain.customer.exception.DuplicateCustomerException;
import com.getir.readingisgood.domain.customer.repository.CustomerDomainRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
  private final CustomerDomainRepository customerDomainRepository;

  public CustomerService(CustomerDomainRepository customerDomainRepository) {
    this.customerDomainRepository = customerDomainRepository;
  }

  public void create(@NonNull CreateCustomer createCustomer) {
    throwIfCustomerAlreadyExists(createCustomer.getEmail());
    var customer = Customer.create(createCustomer);

    customerDomainRepository.save(customer);
  }

  private void throwIfCustomerAlreadyExists(Email email) {
    if (customerStateAlreadyExists(email)) {
      throw new DuplicateCustomerException(email);
    }
  }

  private boolean customerStateAlreadyExists(Email email) {
    return customerDomainRepository.findByEmail(email).isPresent();
  }
}
