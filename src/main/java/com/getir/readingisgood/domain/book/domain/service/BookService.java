package com.getir.readingisgood.domain.book.domain.service;

import com.getir.readingisgood.domain.book.domain.command.CreateBook;
import com.getir.readingisgood.domain.book.domain.core.Book;
import com.getir.readingisgood.domain.book.domain.repository.BookDomainRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class BookService {
  private final BookDomainRepository bookDomainRepository;

  public BookService(BookDomainRepository bookDomainRepository) {
    this.bookDomainRepository = bookDomainRepository;
  }

  public void create(@NonNull CreateBook createBook) {

    var book = Book.create(createBook);

    bookDomainRepository.save(book);
  }
}
