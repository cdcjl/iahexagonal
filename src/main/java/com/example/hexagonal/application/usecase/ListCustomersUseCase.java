package com.example.hexagonal.application.usecase;

import com.example.hexagonal.domain.model.Customer;

import java.util.List;

public interface ListCustomersUseCase {
    List<Customer> list();
}
