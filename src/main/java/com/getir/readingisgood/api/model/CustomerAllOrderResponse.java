package com.getir.readingisgood.api.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerAllOrderResponse {
  List<CustomerOrderData> data;
  MetaData meta;
}
