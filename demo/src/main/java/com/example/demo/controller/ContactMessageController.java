package com.example.demo.controller;


import com.example.demo.model.ContactMessage;
import com.example.demo.service.ContactMessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")

public class ContactMessageController {
    private final ContactMessageService contactMessageService;

    public ContactMessageController(ContactMessageService contactMessageService) {
        this.contactMessageService = contactMessageService;
    }
    @PostMapping
    public ResponseEntity<ContactMessage> sendMessage(@RequestBody ContactMessage message){
        return ResponseEntity.ok(contactMessageService.saveMessage(message));

    }
    @GetMapping
    public  ResponseEntity<List<ContactMessage>> getMessages(){
        return ResponseEntity.ok(contactMessageService.getAllMessages());
    }


}
