package com.example.hexagonal.adapters.in.web;

import java.util.List;

public class CustomerWithOrdersResponse {
    private Long id;
    private String name;
    private String email;
    private List<OrderResponse> orders;

    public CustomerWithOrdersResponse() { }

    public CustomerWithOrdersResponse(Long id, String name, String email, List<OrderResponse> orders) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<OrderResponse> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderResponse> orders) {
        this.orders = orders;
    }
}
