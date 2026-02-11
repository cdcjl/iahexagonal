package com.example.hexagonal.application.usecase;

import com.example.hexagonal.domain.model.Customer;
import com.example.hexagonal.application.dto.UpdateCustomerCommand;

public interface UpdateCustomerUseCase {
    Customer update(Long id, UpdateCustomerCommand command);
}
