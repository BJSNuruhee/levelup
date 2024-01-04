package com.example.demo.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.UserDto;

@Service
public interface UserService {

	// 유저 리스트 조회
	public List<UserDto> findAll();
	
	// 회원가입
	public String postSignUp(UserDto vo);
	
	// 유저 아이디 조회
	public List<String> getUserId();
	
}
