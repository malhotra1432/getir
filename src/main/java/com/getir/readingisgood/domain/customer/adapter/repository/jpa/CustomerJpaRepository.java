package com.getir.readingisgood.domain.customer.adapter.repository.jpa;

import com.getir.readingisgood.domain.customer.adapter.entity.CustomerStateEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerJpaRepository
    extends PagingAndSortingRepository<CustomerStateEntity, Long>,
        JpaRepository<CustomerStateEntity, Long> {

  Optional<CustomerStateEntity> findByEmail(String email);
}
