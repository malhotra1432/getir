package com.getir.readingisgood.domain.order.domain.service;

import com.getir.readingisgood.domain.book.domain.core.Book;
import com.getir.readingisgood.domain.book.domain.core.value.Name;
import com.getir.readingisgood.domain.book.domain.exception.BookNotAvailableException;
import com.getir.readingisgood.domain.book.domain.repository.BookDomainRepository;
import com.getir.readingisgood.domain.customer.core.value.Email;
import com.getir.readingisgood.domain.customer.exception.UnknownCustomerEmailException;
import com.getir.readingisgood.domain.customer.repository.CustomerDomainRepository;
import com.getir.readingisgood.domain.order.domain.command.CreateOrder;
import com.getir.readingisgood.domain.order.domain.core.Order;
import com.getir.readingisgood.domain.order.domain.core.OrderState;
import com.getir.readingisgood.domain.order.domain.repository.OrderDomainRepository;
import java.util.Optional;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
  private final OrderDomainRepository orderDomainRepository;
  private final CustomerDomainRepository customerDomainRepository;
  private final BookDomainRepository bookDomainRepository;

  public OrderService(
      OrderDomainRepository orderDomainRepository,
      CustomerDomainRepository customerDomainRepository,
      BookDomainRepository bookDomainRepository) {
    this.orderDomainRepository = orderDomainRepository;
    this.customerDomainRepository = customerDomainRepository;
    this.bookDomainRepository = bookDomainRepository;
  }

  public void create(@NonNull CreateOrder createOrder) {
    throwIfCustomerDoesNotExists(createOrder.getCustomerEmail());
    throwIfBookDoesNotExists(createOrder.getBookName());

    OrderState orderState = getOrderState(createOrder);
    var order = Order.create(orderState);
    var optionalBook = getBook(createOrder.getBookName());
    final Book book = optionalBook.get();

    orderDomainRepository.save(order);
    bookDomainRepository.deleteById(book.getState().getBookId());
  }

  private OrderState getOrderState(CreateOrder createOrder) {
    Optional<Book> optionalBook = getBook(createOrder.getBookName());
    final Book book = optionalBook.get();

    return OrderState.builder()
        .customerEmail(createOrder.getCustomerEmail())
        .bookName(createOrder.getBookName())
        .bookId(book.getState().getBookId())
        .bookPrice(book.getState().getPrice())
        .build();
  }

  private Optional<Book> getBook(Name bookName) {
    return bookDomainRepository.findByName(bookName);
  }

  private void throwIfCustomerDoesNotExists(Email email) {
    if (!customerStateAlreadyExists(email)) {
      throw new UnknownCustomerEmailException(email);
    }
  }

  private void throwIfBookDoesNotExists(Name bookName) {
    if (!bookStateAlreadyExists(bookName)) {
      throw new BookNotAvailableException(bookName);
    }
  }

  private boolean customerStateAlreadyExists(Email email) {
    return customerDomainRepository.findByEmail(email).isPresent();
  }

  private boolean bookStateAlreadyExists(Name name) {
    return bookDomainRepository.findByNameAndIsAvailable(name, true).isPresent();
  }
}
