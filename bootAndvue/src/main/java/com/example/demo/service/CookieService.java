package com.example.demo.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDto;

@Service
public interface CookieService {

	// 쿠키 로그인
	public String memCookieLogin(UserDto vo, HttpServletResponse response);
	
	// 쿠키 체크
	public String checkCookie(HttpServletRequest req);
	
	// 쿠키 로그아웃
	public ResponseEntity<String> memCookieLogout(HttpServletResponse response);
}
