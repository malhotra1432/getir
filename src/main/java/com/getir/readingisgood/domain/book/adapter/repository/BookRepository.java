package com.getir.readingisgood.domain.book.adapter.repository;

import com.getir.readingisgood.domain.book.adapter.repository.jpa.BookStateJPARepository;
import com.getir.readingisgood.domain.book.adapter.state.BookStateAdapter;
import com.getir.readingisgood.domain.book.domain.core.Book;
import com.getir.readingisgood.domain.book.domain.core.value.BookId;
import com.getir.readingisgood.domain.book.domain.core.value.Name;
import com.getir.readingisgood.domain.book.domain.repository.BookDomainRepository;
import java.util.Optional;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class BookRepository implements BookDomainRepository {
  private final BookStateJPARepository bookStateJPARepository;
  private final BookStateAdapter bookStateAdapter;

  public BookRepository(
      BookStateJPARepository bookStateJPARepository, BookStateAdapter bookStateAdapter) {
    this.bookStateJPARepository = bookStateJPARepository;
    this.bookStateAdapter = bookStateAdapter;
  }

  @Override
  public void save(@NonNull Book book) {
    bookStateJPARepository.save(bookStateAdapter.encode(book.getState()));
    log.info("Successfully updated the state for {}", book.getState().getId());
  }

  @Override
  public Optional<Book> findByName(Name name) {
    return bookStateJPARepository
        .findByName(name.getValue())
        .map(bookStateAdapter::decode)
        .map(Book::create);
  }

  @Override
  public Optional<Book> findByNameAndIsAvailable(Name name, boolean isAvailable) {
    return bookStateJPARepository
        .findByNameAndIsAvailable(name.getValue(), true)
        .map(bookStateAdapter::decode)
        .map(Book::create);
  }

  @Override
  public void deleteById(BookId bookId) {
    bookStateJPARepository.deleteById(bookId.getValue());
  }
}
