package com.example.demo.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.demo.dto.EmailMessage;
import com.example.demo.service.EmailService;

import lombok.RequiredArgsConstructor;

@Service
public class EmailServiceImpl implements EmailService {
	
	@Autowired
	JavaMailSender javaMailSender;
	
    public void sendMail(EmailMessage emailMessage) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(emailMessage.getTo()); // 메일 수신자
            mimeMessageHelper.setSubject(emailMessage.getSubject()); // 메일 제목
            mimeMessageHelper.setText(emailMessage.getMessage(), false); // 메일 본문 내용, HTML 여부
            javaMailSender.send(mimeMessage);
            System.out.println("메시지 전송 성공");
        } catch (MessagingException e) {
            System.out.println("메시지 전송 실패!!!!!");
            throw new RuntimeException(e);
        }
    }
}
