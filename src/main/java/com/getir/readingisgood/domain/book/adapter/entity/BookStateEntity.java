package com.getir.readingisgood.domain.book.adapter.entity;

import java.math.BigDecimal;
import java.time.Instant;
import javax.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "bookState")
public class BookStateEntity {
  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NonNull private String name;
  private BigDecimal price;
  boolean isAvailable;
  @NonNull private Instant createdAt;
}
