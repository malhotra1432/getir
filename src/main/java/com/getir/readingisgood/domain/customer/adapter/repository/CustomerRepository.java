package com.getir.readingisgood.domain.customer.adapter.repository;

import com.getir.readingisgood.domain.customer.adapter.codec.state.CustomerStateAdapter;
import com.getir.readingisgood.domain.customer.adapter.repository.jpa.CustomerJpaRepository;
import com.getir.readingisgood.domain.customer.core.Customer;
import com.getir.readingisgood.domain.customer.core.value.Email;
import com.getir.readingisgood.domain.customer.repository.CustomerDomainRepository;
import java.util.Optional;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class CustomerRepository implements CustomerDomainRepository {
  private final CustomerJpaRepository customerJpaRepository;
  private final CustomerStateAdapter customerStateAdapter;

  public CustomerRepository(
      CustomerJpaRepository customerJpaRepository, CustomerStateAdapter customerStateAdapter) {
    this.customerJpaRepository = customerJpaRepository;
    this.customerStateAdapter = customerStateAdapter;
  }

  @Override
  public void save(@NonNull Customer customer) {
    log.info("Creating customer with email {} ", customer.getState().getEmail());
    customerJpaRepository.save(customerStateAdapter.encode(customer.getState()));
  }

  @Override
  public Optional<Customer> findByEmail(Email email) {
    log.info("Fetch customer with email {} ", email.getValue());
    return customerJpaRepository
        .findByEmail(email.getValue())
        .map(customerStateAdapter::decode)
        .map(Customer::create);
  }
}
