package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;

@RestController
@RequestMapping(value = "/api")
public class TestController {
		
	@Autowired
	UserMapper userMapper;
	
	// 유저 리스트 조회
	@GetMapping("/get/user/list")
	public List<User> findAll() {
		List<User> list = userMapper.getLists();
		return list;
	}
	
	// 회원가입
	@PostMapping("/post/user/signUp")
	public void postSignUp(@RequestBody User vo) {
		userMapper.postSignUp(vo);
	}
	
	// 유저 id 조회
	@GetMapping("/get/user/id")
	public List<String> getUserId() {
		List<String> list = userMapper.getUserId();
		return list;
	}
	
	// 로그인 기능 구현
	@PostMapping("/post/user/login")
	public String memLogin(@RequestBody User vo, HttpSession session) {
		User mvo = userMapper.memLogin(vo);
		if(mvo != null) { // 로그인 성공
			session.setAttribute("mvo", mvo);
			session.setMaxInactiveInterval(1800);
			return "good";
		} else { // 로그인 실패
			return "bad";
		}
	}
	
	// 로그아웃
	@PostMapping("/post/user/logout")
	public String memLogout(HttpSession session) {
		session.invalidate();
		return "1";
	}
	
	
	
	
	
	
	
	
	@RequestMapping(value="/index")
	public String index() {
		Logger logger = LoggerFactory.getLogger(this.getClass());
		logger.info("hello");
		logger.info("This is logger");
		
		return "hello";
	}
	
}
