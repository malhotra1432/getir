package com.getir.readingisgood.domain.order.adapter.entity;

import java.math.BigDecimal;
import java.time.Instant;
import javax.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orderState")
public class OrderStateEntity {
  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column String customerEmail;
  Long bookId;
  @Column String bookName;
  BigDecimal bookPrice;
  @Column Instant createdAt;
}
