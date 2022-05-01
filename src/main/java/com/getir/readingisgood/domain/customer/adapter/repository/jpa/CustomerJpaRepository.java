package com.getir.readingisgood.domain.customer.adapter.repository.jpa;

import com.getir.readingisgood.domain.customer.adapter.entity.CustomerStateEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerJpaRepository
    extends PagingAndSortingRepository<CustomerStateEntity, Long>,
        JpaSpecificationExecutor<CustomerStateEntity> {
  Optional<CustomerStateEntity> findByEmail(String email);
}
