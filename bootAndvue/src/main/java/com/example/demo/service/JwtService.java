package com.example.demo.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import com.example.demo.dto.UserDto;

public interface JwtService {
	
	// JWT 생성
	public String getToken(String key, Object value);
	
	// JWT 로그인
	public ResponseEntity memJWTLogin(UserDto vo, HttpServletResponse res);
	
	// JWT 로그아웃
	public ResponseEntity<String> memJWTLogout(HttpServletResponse res);
	
	// JWT 조회
//	public String getSubject(String token);
	
	// JWT 체크
	public ResponseEntity checkJwt(String token);
	

}
