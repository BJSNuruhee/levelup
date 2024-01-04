package com.example.demo.service.impl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.UserDto;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CookieService;

@Service
public class CookieServiceImpl implements CookieService {

	@Autowired
	UserRepository userRepository;
	
	// 쿠키 로그인
	@Override
	public String memCookieLogin(UserDto dto, HttpServletResponse response) {
		UserDto user = userRepository.memLogin(dto);
		if(user != null) { // 로그인 성공
			// 쿠키에 시간 정보를 주지 않으면 세션 쿠키가 된다. (브라우저 종료시 모두 종료)
			// 쿠키 값은 문자열이여야 하기 때문에 id가 정수일경우 String.valueOf를 통해 문자열로 변환
			// 현재는 스트링으로 되어있어서 쓰지 않아도 무방하다.
			Cookie cookie = new Cookie("memId", String.valueOf(dto.getUserId()));
			
			// 쿠키를 전송할 도메인 지정
			// 도메인 앞에 `.`을 붙이면 해당 주소를 포함한 하위 도메인에서도 사용 가능
			// ex) .naver.com -> shop.naver.com, news.naver.com 다 가능
			// 쿠키는 정해진 도메인 영역에서만 유효하다.
			// 별도의 명시하지 않으면 기본값으로 쿠키를 보낸 서버의 도메인으로 설정된다.
			cookie.setDomain("localhost");
			
//			cookie.setPath("/"); // 모든 경로에서 쿠키 사용
			cookie.setHttpOnly(true); // 스크립트가 쿠키에 접근하는 것을 막음
//			cookie.setSecure(false); // HTTPS 사용시에만 쿠키 정보 전달
			cookie.setMaxAge(60 * 60); // 1시간
			response.addCookie(cookie);
			return user.getUserId();
		} else { // 로그인 실패
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

    // 쿠키 체크
    public String checkCookie(HttpServletRequest req) {
    	// 해당 사이트에서 유효한 쿠키 확인
        // 쿠키에서 사용자 ID를 가져옴
    	Cookie[] cookies = req.getCookies();
    	
        // 쿠키가 있는지 확인
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("memId".equals(cookie.getName())) { // 쿠키의 이름이 "memId"인 경우
                    String cookieId = cookie.getValue();
                    String user = userRepository.findUser(cookieId);
                    if(user != null) {
                    	return cookieId;
                    }
                }
            }
        } 
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "쿠키가 만료되었습니다.");
    }
    
	// 쿠키 로그아웃
	@Override
	public ResponseEntity<String> memCookieLogout(HttpServletResponse response) {
		expiredCookie(response, "memId");
		return ResponseEntity.ok("success");		
	}
	
	private void expiredCookie(HttpServletResponse response, String cookieName) {
	    Cookie cookie = new Cookie(cookieName, null);
	    cookie.setMaxAge(0);
	    response.addCookie(cookie);
	}
	
}
