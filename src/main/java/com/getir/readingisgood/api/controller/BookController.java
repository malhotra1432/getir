package com.getir.readingisgood.api.controller;

import com.getir.readingisgood.domain.book.domain.command.CreateBook;
import com.getir.readingisgood.domain.book.domain.service.BookService;
import com.getir.readingisgood.message.CreateBookMessage;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1/book")
public class BookController {
  private final BookService bookService;

  @Autowired
  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @PostMapping("/create")
  @ResponseStatus(value = HttpStatus.ACCEPTED)
  public ResponseEntity<String> create(@Valid @RequestBody CreateBookMessage createBookMessage) {
    log.info("Create book with name: {}", createBookMessage.getName());
    try {
      CreateBook createBook = CreateBookMessage.toCreateBookCommand(createBookMessage);
      bookService.create(createBook);
      return ResponseEntity.accepted().body("Book created successfully!");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("Book insertion failed!" + e);
    }
  }
}
