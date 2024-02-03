package com.javatechs.orderservice.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.javatechs.orderservice.config.PropertiesConfig;
import com.javatechs.orderservice.dto.*;
import com.javatechs.orderservice.entity.Order;
import com.javatechs.orderservice.feign.OrderServiceProxy;
import com.javatechs.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private MessageSource messageSource;
    @Autowired
    private OrderService service;

    @Autowired
    OrderServiceProxy orderServiceProxy;

    @Autowired
    private PropertiesConfig propertiesConfig;

    @PostMapping(value = "/doOrder")
    public TranscationResponse bookOrder(@Valid @RequestBody TranscationRequest transRequest) {
        return service.saveOrder(transRequest);
    }

    @GetMapping(value = "/orders")
    public CollectionModel<List<Order>> getAllOrders() {
        List<Order> order = service.getOrders();
        CollectionModel enityModel = CollectionModel.of(order);
        WebMvcLinkBuilder webLink = linkTo(methodOn(this.getClass()).getAllOrders());
        enityModel.add(webLink.withRel("All_Orders"));
        return enityModel;
    }

    /*demo for dynamic response json filterin
    Api will filter "price" property from response.
    g*/
    @GetMapping(value = "/order/{id}")
    public MappingJacksonValue getSingleOrder(@PathVariable("id") int id) {
        OrderDto response = service.getOrder(id);
        MappingJacksonValue mapping = new MappingJacksonValue(response);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "qty");
        FilterProvider filters = new SimpleFilterProvider().addFilter("orderFilter", filter);
        mapping.setFilters(filters);


        return mapping;
    }

    @GetMapping(value = "/getOrderPayment/{paymentId}")
    public Payment getOrderPayment(@PathVariable("paymentId") int paymentId) {
        return orderServiceProxy.getPayment(paymentId);
    }


    @GetMapping(value = "/helloWorld")
    public String helloworld() {
        return messageSource.getMessage("good.morning.message", null, "Default Message", LocaleContextHolder.getLocale());
    }

    /* Demo for implementing HATEOUS w */
    @PostMapping(value = "/doOrderhateos")
    public EntityModel<TranscationResponse> bookOrderHeteos(@Valid @RequestBody TranscationRequest transRequest) {
        TranscationResponse response = service.saveOrder(transRequest);
        EntityModel em = EntityModel.of(response);
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllOrders());
        em.add(link.withRel("Hello-world"));


        return em;
    }

    /* Demo  api for getting properties */
    @GetMapping(value = "/limitOrders")
    public Limits printOrderlimit() {

        return new Limits(propertiesConfig.getMinimum(),propertiesConfig.getMaximun());
    }

}
