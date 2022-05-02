package com.getir.readingisgood.domain.book.domain.exception;

import com.getir.readingisgood.domain.book.domain.core.value.Name;
import lombok.Getter;

public class BookNotAvailableException extends BookException {
  @Getter private final Name bookName;

  public BookNotAvailableException(Name bookName) {
    this(bookName, null);
  }

  public BookNotAvailableException(Name bookName, Throwable throwable) {
    super(bookName + " does not exist.", throwable);
    this.bookName = bookName;
  }
}
