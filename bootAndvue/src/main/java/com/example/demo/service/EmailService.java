package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.EmailMessage;

@Service
public interface EmailService {
	
	public void sendMail(EmailMessage emailMassage);
}
