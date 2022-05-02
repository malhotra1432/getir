package com.getir.readingisgood.domain.book.domain.repository;

import com.getir.readingisgood.domain.book.domain.core.Book;
import com.getir.readingisgood.domain.book.domain.core.value.BookId;
import com.getir.readingisgood.domain.book.domain.core.value.Name;
import java.util.Optional;
import lombok.NonNull;

public interface BookDomainRepository {
  void save(@NonNull Book book);

  Optional<Book> findByName(Name name);

  Optional<Book> findByNameAndIsAvailable(Name name, boolean isAvailable);

  void deleteById(BookId bookId);
}
