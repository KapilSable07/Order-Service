package com.javatechs.orderservice.feign;

import com.javatechs.orderservice.dto.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "payment-service",url = "http://localhost:9191/payment/" )
public interface OrderServiceProxy {

    @GetMapping("/getPayment/{paymentId}")
    public Payment getPayment(@PathVariable("paymentId") int paymentId);

}
