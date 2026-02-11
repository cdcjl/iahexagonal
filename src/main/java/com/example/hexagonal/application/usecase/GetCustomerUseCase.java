package com.example.hexagonal.application.usecase;

import com.example.hexagonal.domain.model.Customer;

public interface GetCustomerUseCase {
    Customer getById(Long id);
}
