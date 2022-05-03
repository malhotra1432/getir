package com.getir.readingisgood.domain.book.domain.exception;

import com.getir.readingisgood.domain.book.domain.core.value.Name;
import lombok.Getter;

public class BookNotAvailableException extends BookException {
  @Getter private final Name bookName;

  public BookNotAvailableException(Name bookName) {
    this(bookName, null);
  }

  public BookNotAvailableException(Name bookName, Throwable throwable) {
    super("Stock for book " + bookName.getValue() + " over! Please update book stock!", throwable);
    this.bookName = bookName;
  }
}
