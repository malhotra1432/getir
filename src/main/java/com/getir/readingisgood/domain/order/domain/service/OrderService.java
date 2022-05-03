package com.getir.readingisgood.domain.order.domain.service;

import com.getir.readingisgood.api.controller.model.CustomerAllOrderResponse;
import com.getir.readingisgood.api.controller.model.MonthlyStatisticsData;
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
import com.getir.readingisgood.domain.order.domain.core.value.OrderId;
import com.getir.readingisgood.domain.order.domain.exception.UnknownOrderIdException;
import com.getir.readingisgood.domain.order.domain.repository.OrderDomainRepository;
import java.util.Optional;
import lombok.NonNull;
import org.springframework.data.domain.Pageable;
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
    var isBookAvailable = bookStateAlreadyExists(bookName).getState().isAvailable();
    if (!isBookAvailable) {
      throw new BookNotAvailableException(bookName);
    }
  }

  private boolean customerStateAlreadyExists(Email email) {
    return customerDomainRepository.findByEmail(email).isPresent();
  }

  private Book bookStateAlreadyExists(Name name) {
    return bookDomainRepository
        .findByNameAndIsAvailable(name, true)
        .orElseThrow(() -> new BookNotAvailableException(name));
  }

  public Order getOrderById(OrderId orderId) {
    return orderDomainRepository
        .findById(orderId)
        .orElseThrow(() -> new UnknownOrderIdException(orderId));
  }

  public CustomerAllOrderResponse getOrderByPage(Pageable pageable, Email email) {
    return orderDomainRepository.findByCustomerEmail(pageable, email);
  }

  public MonthlyStatisticsData getCustomerMonthlyStats(Email email) {
    return orderDomainRepository.findByCustomerEmail(email);
  }
}
