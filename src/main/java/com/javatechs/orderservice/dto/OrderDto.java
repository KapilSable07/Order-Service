package com.javatechs.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;

@Data
@Builder
@JsonFilter("orderFilter")
public class OrderDto {
    private int id;

    private String name;
    @Min(value = 5, message = "Quantity must be greater than altleast 5.")
    private int qty;
    @Min(value = 10, message = "Price must be greater than altleast 10$.")
    private double price;
}
