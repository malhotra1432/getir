package com.getir.readingisgood.domain.book.domain.core;

import com.getir.readingisgood.domain.book.domain.core.value.BookId;
import com.getir.readingisgood.domain.book.domain.core.value.Name;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@Data
public class BookState {
  private Long id;
  private BookId bookId;
  @NonNull Name name;
  @NonNull BigDecimal price;
  boolean isAvailable;
  private Instant createdAt;
}
