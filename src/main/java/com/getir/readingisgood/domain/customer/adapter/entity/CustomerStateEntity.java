package com.getir.readingisgood.domain.customer.adapter.entity;

import java.time.Instant;
import javax.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "customerState")
public class CustomerStateEntity {
  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column private String firstName;
  @Column private String lastName;
  @Column private String email;
  @Column private String contactNumber;
  @Column private String address;
  @Column private Instant createdAt;
  @Column private String password;
}
