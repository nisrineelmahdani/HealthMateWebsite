package com.example.demo.service;

import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;


    public List<Order> getAllOrders(){


        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id){
        return orderRepository.findById(id);

    }
    public Order createOrder(Order order){
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id , Order orderDetails){
        return orderRepository.findById(id).map(order -> {
            order.setPatientName(orderDetails.getPatientName());
            order.setAddress(orderDetails.getAddress());
            order.setCity(orderDetails.getCity());
            order.setPostcode(orderDetails.getPostcode());
            order.setPhone(orderDetails.getPhone());
            return orderRepository.save(order);
        }).orElseThrow(() -> new RuntimeException("order not found"));
    }
public void deleteOrder (Long id){
        if(orderRepository.existsById(id)){
            orderRepository.deleteById(id);
        }
        else {
            throw new RuntimeException("Order not found");
        }

}



}
