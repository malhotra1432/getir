package com.getir.readingisgood.api.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.getir.readingisgood.domain.book.domain.command.CreateBook;
import com.getir.readingisgood.domain.book.domain.service.BookService;
import com.getir.readingisgood.message.CreateBookMessage;
import com.getir.readingisgood.message.CreateBookMessageBuilder;
import com.getir.readingisgood.util.Randomizer;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

class BookControllerTest {
  private final BookService bookService = mock(BookService.class);
  private static final Faker faker = Randomizer.create().getFaker();
  private final CreateBookMessageBuilder createCustomerMessageBuilder =
      CreateBookMessageBuilder.create().withName(faker.book().title());

  @Test
  void shouldCreateCustomer() {
    var bookMessage = createCustomerMessageBuilder.buildCreateBookMessage();
    BookController bookController = new BookController(bookService);

    bookController.create(bookMessage);

    verify(bookService).create(buildCreateBookCommand(bookMessage));
  }

  private CreateBook buildCreateBookCommand(CreateBookMessage createBookMessage) {
    return CreateBookMessage.toCreateBookCommand(createBookMessage);
  }
}
