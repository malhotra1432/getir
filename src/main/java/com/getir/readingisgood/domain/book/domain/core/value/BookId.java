package com.getir.readingisgood.domain.book.domain.core.value;

import lombok.NonNull;
import lombok.Value;

@Value
public class BookId {
  @NonNull Long value;

  public static BookId create(Long bookId) {
    return new BookId(bookId);
  }
}
