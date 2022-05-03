package com.getir.readingisgood.api.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MetaData {
  private Integer totalPages;
  private Integer pageNumber;
  private Integer pageSize;
  private long totalElements;
  private Integer numberOfElements;
  private boolean first;
}
