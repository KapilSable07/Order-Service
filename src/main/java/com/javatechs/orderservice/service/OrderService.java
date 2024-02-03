package com.javatechs.orderservice.service;

import com.javatechs.orderservice.dto.OrderDto;
import com.javatechs.orderservice.dto.Payment;
import com.javatechs.orderservice.dto.TranscationRequest;
import com.javatechs.orderservice.dto.TranscationResponse;
import com.javatechs.orderservice.entity.Order;
import com.javatechs.orderservice.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    private OrderRepository repository;

    RestTemplate restTemplate;

    public TranscationResponse saveOrder(TranscationRequest transRequest) {

        OrderDto order = transRequest.getOrder();
        Payment payment = Payment.builder().ordetId(order.getId()).amount(order.getPrice()).build();
        Payment paymentResponse = restTemplate.postForObject("http://PAYMENT-SERVICE/payment/doPayment", payment, Payment.class);
        Order orderObj=Order.builder().id(order.getId()).name(order.getName()).price(order.getPrice()).qty(order.getQty()).build();
        repository.save(orderObj);
        String status = paymentResponse.getPaymentStatus().equals("Success") ? "Trascation Completed" : "Transcation Failed";
        return new TranscationResponse(orderObj, paymentResponse, status);
    }

    public List<Order> getOrders() {

       return repository.findAll();
    }

    public OrderDto getOrder(int id) {
        Order order= repository.findById(id).get();
        OrderDto resopnse= OrderDto.builder().name(order.getName()).price(order.getPrice()).id(order.getId()).qty(order.getQty()).build();
        return resopnse;
    }
}
