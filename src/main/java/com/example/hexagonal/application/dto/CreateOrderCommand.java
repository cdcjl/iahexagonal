package com.example.hexagonal.application.dto;

import java.math.BigDecimal;

public class CreateOrderCommand {
    private Long customerId;
    private String description;
    private BigDecimal amount;
    private String status;

    public CreateOrderCommand() { }

    public CreateOrderCommand(Long customerId, String description, BigDecimal amount, String status) {
        this.customerId = customerId;
        this.description = description;
        this.amount = amount;
        this.status = status;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
