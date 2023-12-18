package com.example.demo.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.jwt.JwtService;
import com.example.demo.mapper.UserMapper;

@RestController
@RequestMapping(value = "/api")
public class UserController {
		
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	JwtService jwtService;
	
	// 유저 리스트 조회
	@GetMapping("/get/user/list")
	public List<User> findAll() {
		List<User> list = userMapper.getLists();
		return list;
	}
	
	// 회원가입
	@PostMapping("/post/user/signUp")
	public void postSignUp(@RequestBody User vo) {
		List<User> list = userMapper.getLists();
		try {
			if(list.stream().noneMatch(d -> 
				d.getUserSeq().equals(vo.getUserId()) ||
				d.getUserId().equals(vo.getUserId()) ||
				d.getUserToken().equals(vo.getUserToken()))) {
				String token = jwtService.createToken(vo.getUserId(), (2*1000*60));
				vo.setUserToken(token);
				userMapper.postSignUp(vo);
				System.out.println("성공");
			}
		} catch (Exception e) {
			System.out.println("실패");
			e.printStackTrace();
		}
		
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
	public void memCookieLogout(HttpServletResponse response) {
		expiredCookie(response, "memId");
	}
	
	private void expiredCookie(HttpServletResponse response, String cookieName) {
	    Cookie cookie = new Cookie(cookieName, null);
	    cookie.setMaxAge(0);
	    response.addCookie(cookie);
	}
	
	// JWT 로그인 기능 구현
	@PostMapping("/post/user/jwtLogin")
	public Map<String, Object> memJWTLogin(@RequestBody User vo) {
		User mvo = userMapper.memLogin(vo);
		if(mvo != null) { // 로그인 성공
			// 토큰 생성 (유저 아이디, 만료 시간 2분)
			String token = jwtService.createToken(vo.getUserId(), (2*1000*60));
			
			// 리턴할 map
			Map<String, Object> map = new LinkedHashMap<>();
			map.put("result", token);
			System.out.println(token);
			return map;
		} else {
			Map<String, Object> map = new LinkedHashMap<>();
			map.put("result", "bad");
			return map;
		}
	}
	
	// 생성한 JWT 토큰 조회하기
	@GetMapping("/get/subject")
	public Map<String, Object> getSubject(@RequestParam(value = "token") String token) {
		String subject = jwtService.getSubject(token);
		
		// 리턴할 map
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("result", subject);
		return map;
	}
	
	
	
	
	
	@RequestMapping(value="/index")
	public String index() {
		Logger logger = LoggerFactory.getLogger(this.getClass());
		logger.info("hello");
		logger.info("This is logger");
		
		return "hello";
	}
	
}
