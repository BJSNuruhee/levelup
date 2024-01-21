package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.EmailMessage;
import com.example.demo.service.EmailService;

@RestController
@RequestMapping(value = "/api")
public class EmailController {
	
	@Autowired
	EmailService emailService;
	
	@PostMapping("/sendMail")
	public ResponseEntity sendMail() {
		EmailMessage emailMessage = new EmailMessage("xodlf100@gmail.com", "테스트 메일 제목", "테스트 메일 본문");
        emailService.sendMail(emailMessage);
        return new ResponseEntity(HttpStatus.OK);
	}
	
}
