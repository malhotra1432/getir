package com.getir.readingisgood.domain.book.adapter.repository.jpa;

import com.getir.readingisgood.domain.book.adapter.entity.BookStateEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookStateJPARepository
    extends PagingAndSortingRepository<BookStateEntity, Long>,
        JpaSpecificationExecutor<BookStateEntity> {

  Optional<BookStateEntity> findByName(String name);

  Optional<BookStateEntity> findByNameAndIsAvailable(String Name, boolean isAvailable);
}
