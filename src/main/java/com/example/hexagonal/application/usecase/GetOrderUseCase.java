package com.example.hexagonal.application.usecase;

import com.example.hexagonal.domain.model.Order;

public interface GetOrderUseCase {
    Order getById(Long id);
}
