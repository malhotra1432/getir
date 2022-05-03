package com.getir.readingisgood.domain.order.domain.repository;

import com.getir.readingisgood.domain.order.domain.core.Order;
import com.getir.readingisgood.domain.order.domain.core.value.OrderId;
import java.util.Optional;
import lombok.NonNull;

public interface OrderDomainRepository {
  void save(@NonNull Order order);

  Optional<Order> findById(OrderId orderId);
}
