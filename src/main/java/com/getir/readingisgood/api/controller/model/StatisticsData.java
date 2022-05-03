package com.getir.readingisgood.api.controller.model;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatisticsData {
  String month;
  Integer totalOrderCount;
  Integer totalBookCount;
  BigDecimal totalPurchasedAmount;
}
