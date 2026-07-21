package com.umar.portfolio.controller;

import com.umar.portfolio.dto.ContactRequest;
import com.umar.portfolio.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "*")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping
    public ResponseEntity<String> sendMessage(@Valid @RequestBody ContactRequest request) 
    {

        contactService.sendContactEmail(request);

        return ResponseEntity.ok("Message sent successfully!");
    }
}