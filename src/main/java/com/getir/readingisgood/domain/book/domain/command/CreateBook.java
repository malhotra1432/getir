package com.getir.readingisgood.domain.book.domain.command;

import com.getir.readingisgood.domain.book.domain.core.BookState;
import com.getir.readingisgood.domain.book.domain.core.value.Name;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class CreateBook {
  @NonNull Name name;
  @NonNull BigDecimal price;

  public BookState toBookState() {
    return BookState.builder().name(name).price(price).build();
  }
}
