package com.getir.readingisgood.domain.customer.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

import com.getir.readingisgood.domain.customer.command.CreateCustomer;
import com.getir.readingisgood.domain.customer.core.Customer;
import com.getir.readingisgood.domain.customer.core.value.Email;
import com.getir.readingisgood.domain.customer.exception.DuplicateCustomerException;
import com.getir.readingisgood.domain.customer.repository.CustomerDomainRepository;
import com.getir.readingisgood.util.TestDataBuilder;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class CustomerServiceTest {
  private final CustomerDomainRepository customerDomainRepository =
      mock(CustomerDomainRepository.class);
  private final CustomerService customerService = new CustomerService(customerDomainRepository);

  @Nested
  class CreateCustomerTest {
    private final Email email = TestDataBuilder.randomEmail();
    private final CreateCustomer.CreateCustomerBuilder createCustomerBuilder =
        TestDataBuilder.randomCreateCustomerBuilder().email(email);

    @BeforeEach
    void setUp() {
      when(customerDomainRepository.findByEmail(email)).thenReturn(Optional.empty());
    }

    @Test
    void shouldCreateCustomer() {
      final CreateCustomer createCustomer = createCustomerBuilder.build();
      customerService.create(createCustomer);
      verify(customerDomainRepository).save(any(Customer.class));
    }

    @Test
    void shouldThrowAlreadyExistExceptionIfCustomerEmailAlreadyExists() {
      when(customerDomainRepository.findByEmail(email))
          .thenReturn(
              Optional.of(
                  Customer.create(
                      TestDataBuilder.randomCustomerStateBuilder().email(email).build())));
      assertThrows(
          DuplicateCustomerException.class,
          () -> customerService.create(createCustomerBuilder.build()));
      verify(customerDomainRepository, times(0)).save(any(Customer.class));
    }
  }
}
