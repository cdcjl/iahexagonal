package com.example.hexagonal.adapters.in.web;

import java.util.List;

public class CustomerWithOrdersResponse {
    private Long id;
    private String name;
    private String email;
    private List<OrderResponse> orders_items;

    public CustomerWithOrdersResponse() { }

    public CustomerWithOrdersResponse(Long id, String name, String email, List<OrderResponse> orders_items) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.orders_items = orders_items;
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

    public List<OrderResponse> getOrders_items() {
        return orders_items;
    }

    public void setOrders_items(List<OrderResponse> orders_items) {
        this.orders_items = orders_items;
    }
}
