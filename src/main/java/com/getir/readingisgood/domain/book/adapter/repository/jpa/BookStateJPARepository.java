package com.getir.readingisgood.domain.book.adapter.repository.jpa;

import com.getir.readingisgood.domain.book.adapter.entity.BookStateEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookStateJPARepository
    extends PagingAndSortingRepository<BookStateEntity, Long>,
        JpaRepository<BookStateEntity, Long> {

  List<BookStateEntity> findByName(String name);

  List<BookStateEntity> findByNameAndIsAvailable(String Name, boolean isAvailable);
}
