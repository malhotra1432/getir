package com.getir.readingisgood.domain.order.domain.repository;

import com.getir.readingisgood.api.controller.model.CustomerAllOrderResponse;
import com.getir.readingisgood.domain.customer.core.value.Email;
import com.getir.readingisgood.domain.order.domain.core.Order;
import com.getir.readingisgood.domain.order.domain.core.value.OrderId;
import java.util.Optional;
import lombok.NonNull;
import org.springframework.data.domain.Pageable;

public interface OrderDomainRepository {
  void save(@NonNull Order order);

  Optional<Order> findById(OrderId orderId);

  CustomerAllOrderResponse findByCustomerEmail(Pageable pageable, Email email);
}
