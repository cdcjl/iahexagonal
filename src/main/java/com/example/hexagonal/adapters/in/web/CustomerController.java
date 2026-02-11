package com.example.hexagonal.adapters.in.web;

import com.example.hexagonal.application.dto.CreateCustomerCommand;
import com.example.hexagonal.application.usecase.CreateCustomerUseCase;
import com.example.hexagonal.application.usecase.DeleteCustomerUseCase;
import com.example.hexagonal.application.usecase.GetCustomerUseCase;
import com.example.hexagonal.application.usecase.ListCustomersUseCase;
import com.example.hexagonal.application.usecase.UpdateCustomerUseCase;
import com.example.hexagonal.domain.model.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final ListCustomersUseCase listCustomersUseCase;
    private final GetCustomerUseCase getCustomerUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;
    private final DeleteCustomerUseCase deleteCustomerUseCase;

    public CustomerController(CreateCustomerUseCase createCustomerUseCase,
                              ListCustomersUseCase listCustomersUseCase,
                              GetCustomerUseCase getCustomerUseCase,
                              UpdateCustomerUseCase updateCustomerUseCase,
                              DeleteCustomerUseCase deleteCustomerUseCase) {
        this.createCustomerUseCase = createCustomerUseCase;
        this.listCustomersUseCase = listCustomersUseCase;
        this.getCustomerUseCase = getCustomerUseCase;
        this.updateCustomerUseCase = updateCustomerUseCase;
        this.deleteCustomerUseCase = deleteCustomerUseCase;
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> create(@RequestBody CreateCustomerCommand command) {
        Customer created = createCustomerUseCase.create(command);
        CustomerResponse resp = new CustomerResponse(created.getId(), created.getName(), created.getEmail());
        return ResponseEntity.created(URI.create("/api/customers/" + created.getId())).body(resp);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> list(@RequestParam(required = false) String q) {
        List<CustomerResponse> resp = listCustomersUseCase.list().stream()
                .map(c -> new CustomerResponse(c.getId(), c.getName(), c.getEmail()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> get(@PathVariable Long id) {
        Customer c = getCustomerUseCase.getById(id);
        return ResponseEntity.ok(new CustomerResponse(c.getId(), c.getName(), c.getEmail()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> update(@PathVariable Long id, @RequestBody com.example.hexagonal.application.dto.UpdateCustomerCommand cmd) {
        Customer updated = updateCustomerUseCase.update(id, cmd);
        return ResponseEntity.ok(new CustomerResponse(updated.getId(), updated.getName(), updated.getEmail()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteCustomerUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
