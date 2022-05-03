package com.getir.readingisgood.domain.order.adapter.repository.jpa;

import com.getir.readingisgood.domain.order.adapter.entity.OrderStateEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderStateJPARepository
    extends PagingAndSortingRepository<OrderStateEntity, Long>,
        JpaSpecificationExecutor<OrderStateEntity> {
  Page<OrderStateEntity> findByCustomerEmail(Pageable pageable, String email);
}
