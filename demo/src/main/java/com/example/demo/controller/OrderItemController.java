package com.example.demo.controller;

import com.example.demo.model.OrderItem;
import com.example.demo.service.OrderItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {
    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;

    }


    @PostMapping
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItem orderItem){
        return ResponseEntity.ok(orderItemService.createOrderItem(orderItem));
    }

    @GetMapping
    public ResponseEntity<List<OrderItem>>getAllOrderItems(){
        return ResponseEntity.ok(orderItemService.getAllOrderItems());
    }
}
