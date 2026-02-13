package com.example.hexagonal.adapters.in.web;

import com.example.hexagonal.application.dto.CreateCustomerCommand;
import com.example.hexagonal.application.usecase.CreateCustomerUseCase;
import com.example.hexagonal.application.usecase.DeleteCustomerUseCase;
import com.example.hexagonal.application.usecase.GetCustomerUseCase;
import com.example.hexagonal.application.usecase.ListCustomersUseCase;
import com.example.hexagonal.application.usecase.UpdateCustomerUseCase;
import com.example.hexagonal.domain.model.Customer;
import com.example.hexagonal.domain.port.out.CustomerRepositoryPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
@Tag(name = "Clientes", description = "Gestión de clientes del sistema")
public class CustomerController {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final ListCustomersUseCase listCustomersUseCase;
    private final GetCustomerUseCase getCustomerUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;
    private final DeleteCustomerUseCase deleteCustomerUseCase;
    private final CustomerRepositoryPort customerRepository;

    public CustomerController(CreateCustomerUseCase createCustomerUseCase,
                              ListCustomersUseCase listCustomersUseCase,
                              GetCustomerUseCase getCustomerUseCase,
                              UpdateCustomerUseCase updateCustomerUseCase,
                              DeleteCustomerUseCase deleteCustomerUseCase,
                              CustomerRepositoryPort customerRepository) {
        this.createCustomerUseCase = createCustomerUseCase;
        this.listCustomersUseCase = listCustomersUseCase;
        this.getCustomerUseCase = getCustomerUseCase;
        this.updateCustomerUseCase = updateCustomerUseCase;
        this.deleteCustomerUseCase = deleteCustomerUseCase;
        this.customerRepository = customerRepository;
    }

    @PostMapping
    @Operation(summary = "Crear nuevo cliente", description = "Crea un nuevo cliente en el sistema")
    public ResponseEntity<CustomerResponse> create(@RequestBody CreateCustomerCommand command) {
        Customer created = createCustomerUseCase.create(command);
        CustomerResponse resp = new CustomerResponse(created.getId(), created.getName(), created.getEmail());
        return ResponseEntity.created(URI.create("/api/customers/" + created.getId())).body(resp);
    }

    @Operation(summary = "Listar clientes", description = "Obtiene la lista de todos los clientes")
    @GetMapping
    public ResponseEntity<List<CustomerResponse>> list(@RequestParam(required = false) String q) {
        List<CustomerResponse> resp = listCustomersUseCase.list().stream()
                .map(c -> new CustomerResponse(c.getId(), c.getName(), c.getEmail()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(resp);
    }

    @Operation(summary = "Obtener cliente", description = "Obtiene un cliente específico por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> get(@PathVariable Long id) {
        Customer c = getCustomerUseCase.getById(id);
        return ResponseEntity.ok(new CustomerResponse(c.getId(), c.getName(), c.getEmail()));
    }
@Operation(summary = "Obtener cliente con órdenes", description = "Obtiene un cliente con todas sus órdenes asociadas")
    
    @GetMapping("/{id}/with-orders")
    public ResponseEntity<CustomerWithOrdersResponse> getWithOrders(@PathVariable Long id) {
        return customerRepository.findByIdWithOrders(id)
                .map(customerWithOrders -> {
                    List<OrderResponse> orderResponses = customerWithOrders.orders.stream()
                            .map(order -> new OrderResponse(
                                    order.getId(),
                                    order.getCustomerId(),
                                    order.getDescription(),
                                    order.getAmount(),
                                    order.getStatus(),
                                    order.getCreatedAt()
                            ))
                            .collect(Collectors.toList());
                    
                    CustomerWithOrdersResponse response = new CustomerWithOrdersResponse(
                            customerWithOrders.customer.getId(),
                            customerWithOrders.customer.getName(),
                            customerWithOrders.customer.getEmail(),
                            orderResponses
                    );
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.notFound().build());
    }
@Operation(summary = "Actualizar cliente", description = "Actualiza los datos de un cliente existente")
    
    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> update(@PathVariable Long id, @RequestBody com.example.hexagonal.application.dto.UpdateCustomerCommand cmd) {
        Customer updated = updateCustomerUseCase.update(id, cmd);
        return ResponseEntity.ok(new CustomerResponse(updated.getId(), updated.getName(), updated.getEmail()));
    }
@Operation(summary = "Eliminar cliente", description = "Elimina un cliente del sistema")
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteCustomerUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
