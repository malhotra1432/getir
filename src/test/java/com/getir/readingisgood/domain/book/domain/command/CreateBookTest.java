package com.getir.readingisgood.domain.book.domain.command;

import static org.assertj.core.api.Assertions.assertThat;

import com.getir.readingisgood.domain.book.domain.core.BookState;
import com.getir.readingisgood.util.TestDataBuilder;
import org.junit.jupiter.api.Test;

class CreateBookTest {
  @Test
  void shouldCreateBookStateFromCommand() {
    CreateBook createBook = TestDataBuilder.randomCreateBookBuilder().build();
    assertThat(createBook.toBookState()).isInstanceOf(BookState.class);
  }
}
