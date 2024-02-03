package com.javatechs.orderservice.dto;

import com.javatechs.orderservice.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class TranscationResponse {

    private Order order;
    private Payment payment;
    private String transcationResponse;

}
