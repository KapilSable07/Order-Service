package com.javatechs.orderservice.feign;

import com.javatechs.orderservice.dto.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// With URL specified client will find service with given URL
//@FeignClient(name = "PAYMENT-SERVICE",url = "http://localhost:9191/" )

// Without URL : Client will finde service by name in service registory
@FeignClient(name = "PAYMENT-SERVICE" )
public interface OrderServiceProxy {

    @GetMapping("/payment/getPayment/{paymentId}")
    public Payment getPayment(@PathVariable("paymentId") int paymentId);

}
