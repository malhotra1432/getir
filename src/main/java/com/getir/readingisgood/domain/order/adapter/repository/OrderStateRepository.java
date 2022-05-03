package com.getir.readingisgood.domain.order.adapter.repository;

import com.getir.readingisgood.api.controller.model.CustomerAllOrderResponse;
import com.getir.readingisgood.api.controller.model.MonthlyStatisticsData;
import com.getir.readingisgood.api.controller.model.StatisticsData;
import com.getir.readingisgood.domain.customer.core.value.Email;
import com.getir.readingisgood.domain.order.adapter.entity.OrderStateEntity;
import com.getir.readingisgood.domain.order.adapter.repository.jpa.OrderStateJPARepository;
import com.getir.readingisgood.domain.order.adapter.state.OrderStateAdapter;
import com.getir.readingisgood.domain.order.domain.core.Order;
import com.getir.readingisgood.domain.order.domain.core.value.OrderId;
import com.getir.readingisgood.domain.order.domain.repository.OrderDomainRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.*;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
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

  @Override
  public MonthlyStatisticsData findByCustomerEmail(Email email) {
    var totalOrderByEmail = orderStateJPARepository.findByCustomerEmail(null, email.getValue());
    return getOrderStatsBYMonth(
        getOrderMonths(totalOrderByEmail.getContent()), totalOrderByEmail.getContent());
  }

  private HashSet<String> getOrderMonths(List<OrderStateEntity> totalOrderByEmail) {
    HashSet<String> monthSet = new HashSet<>();
    for (OrderStateEntity orderStateEntity : totalOrderByEmail) {
      LocalDate localDate = LocalDate.ofInstant(orderStateEntity.getCreatedAt(), ZoneOffset.UTC);
      var orderMonth = localDate.getMonth();
      monthSet.add(orderMonth.toString());
    }
    return monthSet;
  }

  private MonthlyStatisticsData getOrderStatsBYMonth(
      HashSet<String> months, List<OrderStateEntity> totalOrderByEmail) {
    MonthlyStatisticsData.MonthlyStatisticsDataBuilder monthlyStatisticsDataBuilder =
        MonthlyStatisticsData.builder();
    List<StatisticsData> statisticsData = new ArrayList<>();
    for (String month : months) {
      BigDecimal totalPurchasedAmount = BigDecimal.valueOf(0);
      var totalOrderCount = 0;
      var totalBookCount = 0;
      for (OrderStateEntity orderStateEntity : totalOrderByEmail) {
        LocalDate localDate = LocalDate.ofInstant(orderStateEntity.getCreatedAt(), ZoneOffset.UTC);
        var orderMonth = localDate.getMonth().toString();
        if (Objects.equals(orderMonth, month)) {
          totalPurchasedAmount = totalPurchasedAmount.add(orderStateEntity.getBookPrice());
          totalOrderCount += 1;
          totalBookCount += 1;
        }
      }
      statisticsData.add(
          StatisticsData.builder()
              .month(month)
              .totalOrderCount(totalOrderCount)
              .totalBookCount(totalBookCount)
              .totalPurchasedAmount(totalPurchasedAmount)
              .build());
    }
    return monthlyStatisticsDataBuilder.monthlyStats(statisticsData).build();
  }

  @Override
  public CustomerAllOrderResponse findByCustomerEmail(Pageable pageable, Email email) {
    return getOrderResponseByPage(pageable, email);
  }

  private CustomerAllOrderResponse getOrderResponseByPage(Pageable pageable, Email email) {
    CustomerAllOrderResponse customerAllOrderResponse;
    var page = orderStateJPARepository.findByCustomerEmail(pageable, email.getValue());
    customerAllOrderResponse =
        orderStateAdapter.customerAllOrderResponseBuilder(page, email).build();
    return customerAllOrderResponse;
  }
}
