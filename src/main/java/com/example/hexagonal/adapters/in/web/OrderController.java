package com.example.hexagonal.adapters.in.web;

import com.example.hexagonal.application.dto.CreateOrderCommand;
import com.example.hexagonal.application.dto.UpdateOrderCommand;
import com.example.hexagonal.application.usecase.*;
import com.example.hexagonal.domain.model.Order;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Órdenes", description = "Gestión de órdenes del sistema")
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;
    private final ListOrdersUseCase listOrdersUseCase;
    private final ListOrdersByCustomerUseCase listOrdersByCustomerUseCase;
    private final GetOrderUseCase getOrderUseCase;
    private final UpdateOrderUseCase updateOrderUseCase;
    private final DeleteOrderUseCase deleteOrderUseCase;

    public OrderController(CreateOrderUseCase createOrderUseCase,
                           ListOrdersUseCase listOrdersUseCase,
                           ListOrdersByCustomerUseCase listOrdersByCustomerUseCase,
                           GetOrderUseCase getOrderUseCase,
                           UpdateOrderUseCase updateOrderUseCase,
                           DeleteOrderUseCase deleteOrderUseCase) {
        this.createOrderUseCase = createOrderUseCase;
        this.listOrdersUseCase = listOrdersUseCase;
        this.listOrdersByCustomerUseCase = listOrdersByCustomerUseCase;
        this.getOrderUseCase = getOrderUseCase;
        this.updateOrderUseCase = updateOrderUseCase;
        this.deleteOrderUseCase = deleteOrderUseCase;
    }

    @PostMapping
    @Operation(summary = "Crear nueva orden", description = "Crea una nueva orden en el sistema")
    public ResponseEntity<OrderResponse> create(@RequestBody CreateOrderCommand command) {
        Order order = createOrderUseCase.create(command);
        OrderResponse response = fromOrder(order);
        return ResponseEntity.created(URI.create("/api/orders/" + order.getId())).body(response);
    }

    @Operation(summary = "Listar órdenes", description = "Obtiene la lista de todas las órdenes")
    @GetMapping
    public ResponseEntity<List<OrderResponse>> list() {
        List<Order> orders = listOrdersUseCase.list();
        List<OrderResponse> responses = orders.stream().map(this::fromOrder).collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @Operation(summary = "Listar órdenes por cliente", description = "Obtiene todas las órdenes de un cliente específico")
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderResponse>> listByCustomer(@PathVariable Long customerId) {
        List<Order> orders = listOrdersByCustomerUseCase.listByCustomer(customerId);
        List<OrderResponse> responses = orders.stream().map(this::fromOrder).collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }
@Operation(summary = "Obtener orden", description = "Obtiene una orden específica por su ID")
    
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getById(@PathVariable Long id) {
        Order order = getOrderUseCase.getById(id);
        return ResponseEntity.ok(fromOrder(order));
    }
@Operation(summary = "Actualizar orden", description = "Actualiza los datos de una orden existente")
    
    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> update(@PathVariable Long id, @RequestBody UpdateOrderCommand command) {
        Order order = updateOrderUseCase.update(id, command);
        return ResponseEntity.ok(fromOrder(order));
    }
@Operation(summary = "Eliminar orden", description = "Elimina una orden del sistema")
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteOrderUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

    private OrderResponse fromOrder(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getCustomerId(),
                order.getDescription(),
                order.getAmount(),
                order.getStatus(),
                order.getCreatedAt()
        );
    }
}
