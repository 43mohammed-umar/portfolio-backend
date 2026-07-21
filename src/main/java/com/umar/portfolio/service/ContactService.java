package com.umar.portfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.umar.portfolio.dto.ContactRequest;

@Service
public class ContactService {

	private final JavaMailSender mailSender;
	private static final Logger logger = LoggerFactory.getLogger(ContactService.class);

	public ContactService(JavaMailSender mailSender) {
	    this.mailSender = mailSender;
	}

    @Value("${portfolio.contact.receiver-email}")
    private String receiverEmail;

    public void sendContactEmail(ContactRequest request)
    {

        logger.info("Contact request received from: {}", request.getEmail());

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(receiverEmail);
        message.setSubject("New Portfolio Contact : " + request.getSubject());

        message.setText(
                "Name : " + request.getFullName() + "\n\n" +
                "Email : " + request.getEmail() + "\n\n" +
                "Phone : " + request.getPhone() + "\n\n" +
                "Message : \n\n" +
                request.getMessage()
        );

        logger.info("Sending email...");

        mailSender.send(message);

        logger.info("Email sent successfully to {}", receiverEmail);
    }
}