package com.test.springboot.service;

import com.test.springboot.dto.OrderRequest;
import com.test.springboot.dto.OrderResponse;

public interface OrderService {
    OrderResponse placeOrder(OrderRequest orderRequest);
}
