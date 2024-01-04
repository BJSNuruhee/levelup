package com.example.demo.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDto;

@Service
public interface SessionService {

	// 세션 로그인
	public String memLogin(UserDto vo, HttpServletRequest httpServletRequest);
	
	// 세션 체크
	public String checkSession(HttpSession session);
	
	// 세션 로그아웃
	public ResponseEntity<String> memLogout(HttpSession session);
}
