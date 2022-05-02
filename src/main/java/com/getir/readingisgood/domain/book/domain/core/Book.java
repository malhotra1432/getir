package com.getir.readingisgood.domain.book.domain.core;

import com.getir.readingisgood.domain.book.domain.command.CreateBook;
import lombok.Getter;
import lombok.NonNull;

public class Book {
  @Getter private final BookState state;

  public Book(BookState bookState) {
    this.state = bookState;
  }

  public static Book create(@NonNull CreateBook createBook) {
    return create(createBook.toBookState());
  }

  public static Book create(@NonNull BookState bookState) {
    return new Book(bookState);
  }
}
