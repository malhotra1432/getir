package com.getir.readingisgood.domain.order.adapter.state;

import com.getir.readingisgood.api.controller.model.CustomerAllOrderResponse;
import com.getir.readingisgood.api.controller.model.CustomerOrderData;
import com.getir.readingisgood.api.controller.model.MetaData;
import com.getir.readingisgood.domain.book.domain.core.value.BookId;
import com.getir.readingisgood.domain.book.domain.core.value.Name;
import com.getir.readingisgood.domain.customer.core.value.Email;
import com.getir.readingisgood.domain.order.adapter.entity.OrderStateEntity;
import com.getir.readingisgood.domain.order.domain.core.OrderState;
import com.getir.readingisgood.domain.order.domain.core.value.OrderId;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class OrderStateAdapter {

  public OrderState decode(OrderStateEntity entity) {
    return OrderState.builder()
        .id(OrderId.create(entity.getId()))
        .customerEmail(Email.create(entity.getCustomerEmail()))
        .bookId(BookId.create(entity.getBookId()))
        .bookName(Name.create(entity.getBookName()))
        .bookPrice(entity.getBookPrice())
        .createdAt(entity.getCreatedAt())
        .build();
  }

  public OrderStateEntity encode(OrderState orderState) {
    return OrderStateEntity.builder()
        .customerEmail(orderState.getCustomerEmail().getValue())
        .bookId(orderState.getBookId().getValue())
        .bookName(orderState.getBookName().getValue())
        .bookPrice(orderState.getBookPrice())
        .createdAt(Instant.now())
        .build();
  }

  public CustomerAllOrderResponse.CustomerAllOrderResponseBuilder customerAllOrderResponseBuilder(
      Page<OrderStateEntity> response, Email email) {
    var orderStateEntityList = response.getContent();
    List<CustomerOrderData> customerOrderDataList = new ArrayList<>();
    var metaData =
        MetaData.builder()
            .first(response.isFirst())
            .pageNumber(response.getNumber())
            .pageSize(response.getSize())
            .totalPages(response.getTotalPages())
            .totalElements(response.getTotalElements())
            .numberOfElements(response.getNumberOfElements())
            .build();
    for (OrderStateEntity data : orderStateEntityList) {
      customerOrderDataList.add(
          CustomerOrderData.builder()
              .id(data.getId())
              .bookName(data.getBookName())
              .customerEmail(data.getCustomerEmail())
              .bookPrice(data.getBookPrice())
              .bookId(data.getBookId())
              .createdAt(data.getCreatedAt())
              .build());
    }
    return CustomerAllOrderResponse.builder().data(customerOrderDataList).meta(metaData);
  }
}
