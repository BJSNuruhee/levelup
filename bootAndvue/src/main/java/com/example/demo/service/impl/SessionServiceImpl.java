package com.example.demo.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.UserDto;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.SessionService;

@Service
public class SessionServiceImpl implements SessionService {

	@Autowired
	UserRepository userRepository;
	
	// 세션 로그인 구현
	@Override
	public String memLogin(UserDto vo, HttpServletRequest request) {
		UserDto user = userRepository.memLogin(vo);
		if(user != null) { // 로그인 성공 => 세션 생성
			// 세션을 생성하기 전에 기존의 세션 파기
			request.getSession().invalidate();

	        // 세션이 없으면 생성
	        HttpSession session = request.getSession(true);
	        
	        // 세션에 userId를 넣어줌
			session.setAttribute("userId", user.getUserId());
			
			// 세션을 30분 동안 유지
			session.setMaxInactiveInterval(1800);
			
			return user.getUserId();
		} else { // 로그인 실패
			// http 응답코드를 리턴한다.
			// DB에 정보가 없을 시 404 리턴
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
    // 세션 체크
    public String checkSession(HttpSession session) {
        // 사용자가 login 데이터를 요청
    	// 서버에서 해당 쿠키를 검증하기 위해 session.getAttribute로 세션Id를 가져옴
        String user = (String) session.getAttribute("userId");
        System.out.println(user);

        // 쿠키에서 넘어온 세션 Id 유효성 검증
        if (user != null) {
        	System.out.println("유효성 검증 통과");
            return user;
        } else {
            // 세션에 사용자 ID가 없는 경우 401 리턴
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "세션이 만료되었습니다.");
        }
    }
    
	// 세션 로그아웃
    // ResponseEntity : 클라이언트의 HttpRequest에 대한 응답데이터를 포함하는 클래스
	@Override
	public ResponseEntity<String> memLogout(HttpSession session) {
		session.invalidate();
		return ResponseEntity.ok("success");
	}


}
