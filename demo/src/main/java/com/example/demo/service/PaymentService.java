package com.example.demo.service;

import com.example.demo.model.Payment;
import com.example.demo.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public Payment createPayment(Payment payment){
       return paymentRepository.save(payment);

    }

    public Optional<Payment> getPaymentById(Long id){

        return paymentRepository.findById(id);
    }

    public List<Payment> getPaymentByOrderId(Long orderId){
        return paymentRepository.findByOrderId(orderId);
    }

    public void deletePayment (Long payId){
        paymentRepository.deleteById(payId);
    }
    public Payment updatePayment(Long paymentId, Payment updatedPayment) {
        if (paymentRepository.existsById(paymentId)) {
            updatedPayment.setId(paymentId);
            return paymentRepository.save(updatedPayment);
        } else {
            // Handle payment not found case
            throw new RuntimeException("Payment not found");
        }
    }

    public List<Payment> getPaymentsByOrderId(Long orderId) {
        return  paymentRepository.findByOrderId(orderId);
    }
}
