package com.test.springboot.dto;

import com.test.springboot.entities.Order;
import com.test.springboot.entities.Payment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
    private Order order;
    private Payment payment;
}
