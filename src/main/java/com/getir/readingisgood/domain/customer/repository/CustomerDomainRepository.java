package com.getir.readingisgood.domain.customer.repository;

import com.getir.readingisgood.domain.customer.core.Customer;
import com.getir.readingisgood.domain.customer.core.value.Email;
import java.util.Optional;
import lombok.NonNull;

public interface CustomerDomainRepository {
  void save(@NonNull Customer customer);

  Optional<Customer> findByEmail(Email email);
}
