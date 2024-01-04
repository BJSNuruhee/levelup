package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDto;
import com.example.demo.service.CookieService;
import com.example.demo.service.JwtService;
import com.example.demo.service.SessionService;
import com.example.demo.service.UserService;

import io.jsonwebtoken.Claims;

@RestController
@RequestMapping(value = "/api")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	SessionService sessionService;

	@Autowired
	CookieService cookieService;

	@Autowired
	JwtService jwtService;

	// 유저 리스트 조회
	@GetMapping("/get/user/list")
	public List<UserDto> findAll() {
		return userService.findAll();
	}

	// 회원가입
	@PostMapping("/post/user/signUp")
	public String postSignUp(@RequestBody UserDto vo) {
		return userService.postSignUp(vo);
	}

	// 유저 id 조회
	@GetMapping("/get/user/id")
	public List<String> getUserId() {
		return userService.getUserId();
	}

	// 세션 로그인
	@PostMapping("/post/user/login")
	public String memLogin(@RequestBody UserDto vo, HttpServletRequest req) {
		return sessionService.memLogin(vo, req);
	}

	// 세션 체크
	@PostMapping("post/user/checkSession")
	public String checkSession(HttpSession session) {
		return sessionService.checkSession(session);
	}

	// 세션 로그아웃
	@PostMapping("/post/user/logout")
	public ResponseEntity<String> memLogout(HttpSession session) {
		return sessionService.memLogout(session);
	}

	// 쿠키 로그인
	@PostMapping("/post/user/cookieLogin")
	public String memCookieLogin(@RequestBody UserDto dto, HttpServletResponse res) {
		return cookieService.memCookieLogin(dto, res);
	}

	// 쿠키 체크
	@PostMapping("post/user/checkCookie")
	public String checkCookie(HttpServletRequest req) {
		return cookieService.checkCookie(req);
	}

	// 쿠키 로그아웃
	@PostMapping("/post/user/cookieLogout")
	public ResponseEntity<String> memCookieLogout(HttpServletResponse res) {
		return cookieService.memCookieLogout(res);
	}

	// JWT 로그인
	@PostMapping("/post/user/jwtLogin")
	public ResponseEntity memJWTLogin(@RequestBody UserDto vo, HttpServletResponse res) {
		return jwtService.memJWTLogin(vo, res);
	}

	// JWT 체크
	@PostMapping("post/user/checkJwt")
	public ResponseEntity checkJwt(@CookieValue(value = "token", required = false) String token) {
		return jwtService.checkJwt(token);
	}
	
	// JWT 로그아웃
	@PostMapping("/post/user/jwtLogout")
	public ResponseEntity<String> memJWTLogout(HttpServletResponse res) {
		return jwtService.memJWTLogout(res);
	}


	// 생성한 JWT 토큰 조회하기
	// @GetMapping("/get/subject")
	// public String getSubject(@RequestParam(value = "token") String token) {
	// return jwtService.getSubject(token);
	// }

	@RequestMapping(value = "/index")
	public String index() {
		Logger logger = LoggerFactory.getLogger(this.getClass());
		logger.info("hello");
		logger.info("This is logger");

		return "hello";
	}

}
