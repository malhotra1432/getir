package com.getir.readingisgood.domain.book.adapter.state;

import com.getir.readingisgood.domain.book.adapter.entity.BookStateEntity;
import com.getir.readingisgood.domain.book.domain.core.BookState;
import com.getir.readingisgood.domain.book.domain.core.value.BookId;
import com.getir.readingisgood.domain.book.domain.core.value.Name;
import java.time.Instant;
import org.springframework.stereotype.Component;

@Component
public class BookStateAdapter {

  public BookState decode(BookStateEntity entity) {
    return BookState.builder()
        .id(entity.getId())
        .bookId(BookId.create(entity.getId()))
        .name(Name.create(entity.getName()))
        .price(entity.getPrice())
        .createdAt(entity.getCreatedAt())
        .isAvailable(entity.isAvailable())
        .build();
  }

  public BookStateEntity encode(BookState bookState) {
    return BookStateEntity.builder()
        .name(bookState.getName().getValue())
        .price(bookState.getPrice())
        .createdAt(Instant.now())
        .isAvailable(true)
        .build();
  }
}
