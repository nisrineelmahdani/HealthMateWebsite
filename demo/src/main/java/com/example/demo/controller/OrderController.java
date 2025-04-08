package com.example.demo.controller;

import com.example.demo.model.Order;
import com.example.demo.model.OrderItem;
import com.example.demo.model.Payment;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.OrderItemRepository;
import com.example.demo.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    // Créer une commande
    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order savedOrder = orderRepository.save(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    // Créer un paiement
    @PostMapping("/payment")
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
        Payment savedPayment = paymentRepository.save(payment);
        return new ResponseEntity<>(savedPayment, HttpStatus.CREATED);
    }

    // Ajouter un article à une commande existante
    @PostMapping("/{orderId}/item")
    public OrderItem addItemToOrder(@PathVariable Long orderId, @RequestBody OrderItem orderItem) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Associer l'ordre à l'item
        orderItem.setOrder(order);

        // Vérifier si la medicine existe (si nécessaire)
        if (orderItem.getMedicine() == null || orderItem.getMedicine().getId() == null) {
            throw new RuntimeException("Medicine is required");
        }

        // Vérifier si la pharmacie existe (si nécessaire)
        if (orderItem.getPharmacy() == null || orderItem.getPharmacy().getId() == null) {
            throw new RuntimeException("Pharmacy is required");
        }

        return orderItemRepository.save(orderItem);
    }


    // Récupérer toutes les commandes
    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Gestion des erreurs globales (facultatif, mais recommande si nécessaire)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleNotFound(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
