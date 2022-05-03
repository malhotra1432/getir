package com.getir.readingisgood.domain.order.domain.repository;

import com.getir.readingisgood.domain.order.domain.core.Order;
import lombok.NonNull;

public interface OrderDomainRepository {
  void save(@NonNull Order order);
}
