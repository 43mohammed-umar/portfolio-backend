package com.umar.portfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.umar.portfolio.dto.ContactRequest;

@Service
public class ContactService {

	private final JavaMailSender mailSender;

	public ContactService(JavaMailSender mailSender) {
	    this.mailSender = mailSender;
	}

    @Value("${portfolio.contact.receiver-email}")
    private String receiverEmail;

    public void sendContactEmail(ContactRequest request) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(receiverEmail);
        message.setSubject("Boss You Got This Mail Is  : " + request.getSubject());

        message.setText(
                "Name : " + request.getFullName() + "\n\n" +
                "Email : " + request.getEmail() + "\n\n" +
                "Phone : " + request.getPhone() + "\n\n" +
                "Message : \n\n" +
                request.getMessage()
        );

        mailSender.send(message);
    }
}