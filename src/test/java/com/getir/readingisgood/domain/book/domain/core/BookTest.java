package com.getir.readingisgood.domain.book.domain.core;

import static org.assertj.core.api.Assertions.assertThat;

import com.getir.readingisgood.domain.book.domain.command.CreateBook;
import com.getir.readingisgood.util.TestDataBuilder;
import org.junit.jupiter.api.Test;

class BookTest {
  @Test
  void shouldCreateBookDomainFromCommand() {
    CreateBook createBook = TestDataBuilder.randomCreateBookBuilder().build();
    var bookState = createBook.toBookState();
    assertThat(Book.create(bookState)).isInstanceOf(Book.class);
  }
}
