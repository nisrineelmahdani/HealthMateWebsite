package com.example.demo.service;
import com.example.demo.model.ContactMessage;
import com.example.demo.model.Pharmacy;
import com.example.demo.repository.ContactMessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ContactMessageService {

    private final ContactMessageRepository contactMessageRepository;

    public ContactMessageService(ContactMessageRepository contactMessageRepository){

        this.contactMessageRepository = contactMessageRepository;
    }


    public ContactMessage saveMessage(ContactMessage message) {
        return contactMessageRepository.save(message);
    }

    public List<ContactMessage> getAllMessages() {
        return contactMessageRepository.findAll();
    }
}
