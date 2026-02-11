package com.example.hexagonal.application.service;

import com.example.hexagonal.application.dto.CreateCustomerCommand;
import com.example.hexagonal.application.dto.UpdateCustomerCommand;
import com.example.hexagonal.application.exception.ResourceNotFoundException;
import com.example.hexagonal.application.usecase.*;
import com.example.hexagonal.domain.model.Customer;
import com.example.hexagonal.domain.port.out.CustomerRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements CreateCustomerUseCase, ListCustomersUseCase, GetCustomerUseCase, UpdateCustomerUseCase, DeleteCustomerUseCase {

    private final CustomerRepositoryPort repository;

    public CustomerService(CustomerRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Customer create(CreateCustomerCommand command) {
        Customer c = new Customer(null, command.getName(), command.getEmail());
        return repository.save(c);
    }

    @Override
    public List<Customer> list() {
        return repository.findAll();
    }

    @Override
    public Customer getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found: " + id));
    }

    @Override
    public Customer update(Long id, UpdateCustomerCommand command) {
        Customer existing = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found: " + id));
        existing.setName(command.getName());
        existing.setEmail(command.getEmail());
        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        Customer existing = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found: " + id));
        repository.deleteById(id);
    }
}
