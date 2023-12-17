package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
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
	
	// 쿠키 로그인 기능 구현
	@PostMapping("/post/user/cookieLogin")
	public String memCookieLogin(@RequestBody User vo, HttpServletResponse response) {
		User mvo = userMapper.memLogin(vo);
		if(mvo != null) { // 로그인 성공
			//쿠키에 시간 정보를 주지 않으면 세션 쿠키가 된다. (브라우저 종료시 모두 종료)
			Cookie cookie = new Cookie("memId", String.valueOf(vo.getUserId()));
			cookie.setMaxAge(60 * 60); // 1시간
			response.addCookie(cookie);
			return "good";
		} else { // 로그인 실패
			return "bad";
		}
	}
	
	// 쿠키 로그아웃
	@PostMapping("/post/user/cookieLogout")
	public String memCookieLogout(HttpServletResponse response) {
		expiredCookie(response, "memId");
		return "1";
	}
	
	private void expiredCookie(HttpServletResponse response, String cookieName) {
	    Cookie cookie = new Cookie(cookieName, null);
	    cookie.setMaxAge(0);
	    response.addCookie(cookie);
	}
	
	
	
	
	
	
	
	
	@RequestMapping(value="/index")
	public String index() {
		Logger logger = LoggerFactory.getLogger(this.getClass());
		logger.info("hello");
		logger.info("This is logger");
		
		return "hello";
	}
	
}
