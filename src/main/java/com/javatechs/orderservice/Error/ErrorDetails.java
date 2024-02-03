package com.javatechs.orderservice.Error;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Builder
public class ErrorDetails {

    private LocalDateTime time;
    private String errorMessage;
    private String description;

}
