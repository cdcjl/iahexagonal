package com.example.hexagonal.application.usecase;

import com.example.hexagonal.application.dto.UpdateOrderCommand;
import com.example.hexagonal.domain.model.Order;

public interface UpdateOrderUseCase {
    Order update(Long id, UpdateOrderCommand command);
}
