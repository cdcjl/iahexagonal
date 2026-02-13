package com.example.hexagonal.application.service;

import com.example.hexagonal.application.dto.CreateOrderCommand;
import com.example.hexagonal.application.dto.UpdateOrderCommand;
import com.example.hexagonal.application.exception.ResourceNotFoundException;
import com.example.hexagonal.application.usecase.*;
import com.example.hexagonal.domain.model.Order;
import com.example.hexagonal.domain.port.out.OrderRepositoryPort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService implements CreateOrderUseCase, ListOrdersUseCase, ListOrdersByCustomerUseCase, GetOrderUseCase, UpdateOrderUseCase, DeleteOrderUseCase {

    private final OrderRepositoryPort repository;

    public OrderService(OrderRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Order create(CreateOrderCommand command) {
        Order order = new Order(
                null,
                command.getCustomerId(),
                command.getDescription(),
                command.getAmount(),
                command.getStatus(),
                LocalDateTime.now()
        );
        return repository.save(order);
    }

    @Override
    public List<Order> list() {
        return repository.findAll();
    }

    @Override
    public List<Order> listByCustomer(Long customerId) {
        return repository.findByCustomerId(customerId);
    }

    @Override
    public Order getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found: " + id));
    }

    @Override
    public Order update(Long id, UpdateOrderCommand command) {
        Order existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found: " + id));
        
        existing.setDescription(command.getDescription());
        existing.setAmount(command.getAmount());
        existing.setStatus(command.getStatus());
        
        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        Order existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found: " + id));
        repository.deleteById(id);
    }
}
