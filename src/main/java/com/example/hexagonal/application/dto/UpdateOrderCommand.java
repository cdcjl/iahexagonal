package com.example.hexagonal.application.dto;

import java.math.BigDecimal;

public class UpdateOrderCommand {
    private String description;
    private BigDecimal amount;
    private String status;

    public UpdateOrderCommand() { }

    public UpdateOrderCommand(String description, BigDecimal amount, String status) {
        this.description = description;
        this.amount = amount;
        this.status = status;
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
