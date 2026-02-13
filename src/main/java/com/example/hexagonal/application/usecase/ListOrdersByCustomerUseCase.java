package com.example.hexagonal.application.usecase;

import com.example.hexagonal.domain.model.Order;

import java.util.List;

public interface ListOrdersByCustomerUseCase {
    List<Order> listByCustomer(Long customerId);
}
