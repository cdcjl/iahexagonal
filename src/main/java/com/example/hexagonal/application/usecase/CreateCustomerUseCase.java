package com.example.hexagonal.application.usecase;

import com.example.hexagonal.domain.model.Customer;
import com.example.hexagonal.application.dto.CreateCustomerCommand;

public interface CreateCustomerUseCase {
    Customer create(CreateCustomerCommand command);
}
