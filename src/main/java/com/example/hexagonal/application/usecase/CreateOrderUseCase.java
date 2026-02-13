package com.example.hexagonal.application.usecase;

import com.example.hexagonal.application.dto.CreateOrderCommand;
import com.example.hexagonal.domain.model.Order;

public interface CreateOrderUseCase {
    Order create(CreateOrderCommand command);
}
