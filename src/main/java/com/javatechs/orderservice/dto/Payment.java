package com.javatechs.orderservice.dto;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
public class Payment {
  private int paymentId;
  private String paymentStatus;
  private String transcationId;
  private int ordetId;
  private double amount;

}
