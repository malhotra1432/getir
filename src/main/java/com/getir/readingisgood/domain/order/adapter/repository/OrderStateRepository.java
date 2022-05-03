package com.getir.readingisgood.domain.order.adapter.repository;

import com.getir.readingisgood.domain.order.adapter.repository.jpa.OrderStateJPARepository;
import com.getir.readingisgood.domain.order.adapter.state.OrderStateAdapter;
import com.getir.readingisgood.domain.order.domain.core.Order;
import com.getir.readingisgood.domain.order.domain.core.value.OrderId;
import com.getir.readingisgood.domain.order.domain.repository.OrderDomainRepository;
import java.util.Optional;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class OrderStateRepository implements OrderDomainRepository {

  private final OrderStateJPARepository orderStateJPARepository;
  private final OrderStateAdapter orderStateAdapter;

  public OrderStateRepository(
      OrderStateJPARepository orderStateJPARepository, OrderStateAdapter orderStateAdapter) {
    this.orderStateJPARepository = orderStateJPARepository;
    this.orderStateAdapter = orderStateAdapter;
  }

  @Override
  public void save(@NonNull Order order) {
    var stateEntity = orderStateAdapter.encode(order.getState());
    this.orderStateJPARepository.save(stateEntity);
    log.info("Successfully updated the state for {}", order.getState().getId());
  }

  @Override
  public Optional<Order> findById(OrderId orderId) {
    return orderStateJPARepository
        .findById(orderId.getValue())
        .map(orderStateAdapter::decode)
        .map(Order::create);
  }
}
