package com.example.demo.service.impl;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.UserDto;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.JwtService;
import com.example.demo.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtServiceImpl implements JwtService {

	@Autowired
	UserRepository userRepository;

	// JWT 비밀키
	private String secretKey = "abvcbcxsdfjoijsdoifj2374iluhaf823fa28j3fla982j3fl9a823jf@##$!$jaiowef";

	// JWT 생성 메서드
	@Override
	public String getToken(String key, Object value) {

		Date expTime = new Date();
		expTime.setTime(expTime.getTime() + 1000 * 60 * 5); // 5분
		byte[] secretBytekey = DatatypeConverter.parseBase64Binary(secretKey);
		Key signKey = new SecretKeySpec(secretBytekey, SignatureAlgorithm.HS256.getJcaName());

		Map<String, Object> headerMap = new HashMap<>();
		headerMap.put("typ", "JWT");
		headerMap.put("alg", "HS256");

		Map<String, Object> map = new HashMap<>();
		map.put(key, value);

		JwtBuilder builder = Jwts.builder().setHeader(headerMap).setClaims(map).setExpiration(expTime).signWith(signKey,
				SignatureAlgorithm.HS256);

		return builder.compact();
	}

	// JWT 로그인
	@Override
	public ResponseEntity memJWTLogin(UserDto dto, HttpServletResponse res) {
		UserDto mvo = userRepository.memLogin(dto);
		if (mvo != null) { // 로그인 성공
			String id = mvo.getUserId();
			Cookie cookie = new Cookie("token", mvo.getUserToken());
			cookie.setHttpOnly(true);
			cookie.setMaxAge(60 * 60);
			res.addCookie(cookie);
			
			return new ResponseEntity<>(id, HttpStatus.OK);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	// JWT 로그인 체크 메서드
	public Claims getClaims(String token) {
		if (token != null && !"".equals(token)) {
			try {
				byte[] secretBytekey = DatatypeConverter.parseBase64Binary(secretKey);
				Key signKey = new SecretKeySpec(secretBytekey, SignatureAlgorithm.HS256.getJcaName());
				return Jwts.parserBuilder().setSigningKey(signKey).build().parseClaimsJws(token).getBody();
			} catch (ExpiredJwtException e) {
				// 토큰이 만료됨
			} catch (JwtException e) {
				// 유효하지 않을 때
			}
		}
		return null;
	}

	// JWT 로그인 체크
	@Override
	public ResponseEntity checkJwt(String token) {
		System.out.println(token);
		Claims claims = getClaims(token);
		System.out.println(claims);
	    if (claims == null) {
	        return new ResponseEntity<>("Invalid token", HttpStatus.BAD_REQUEST);
	    }
	    
	    // token 생성시 key를 "id"로 하였기 때문에, "id"를 통해 value를 얻는다.
		String id = claims.get("id").toString();
		
		// token의 value값을 이용해 db에서 해당 userId의 정보를 찾는다.
		UserDto dto = userRepository.findUserDetail(id);
		System.out.println(id);
		// 브라우저 쿠키 token 값과 DB의 token 값을 비교해 유효성 검증
		if (dto.getUserToken().equals(token)) {
			return new ResponseEntity<>(id, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	// 로그아웃
	@Override
	public ResponseEntity<String> memJWTLogout(HttpServletResponse res) {
		expiredCookie(res, "token");
		return ResponseEntity.ok("success");		
	}
	
	private void expiredCookie(HttpServletResponse response, String cookieName) {
	    Cookie cookie = new Cookie(cookieName, null);
	    cookie.setMaxAge(0);
	    response.addCookie(cookie);
	}
	
	
//	// 비밀키 생성
//	private static final String SECRET_KEY = "asafdasdfajijoijfiajoijawoiejfiojijojiefiowjef";
//
//	// 토큰 구현 메서드
//	public String createToken(String subject, long expTime) {
//		if (expTime <= 0) {
//			throw new RuntimeException("만료 시간은 0보다 커야함");
//		}
//
//		// 시그니처 알고리즘 선택
//		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
//
//		// 스트링 형태의 key를 바이트 단위의 key로 변환
//		// jaxb-api : DatatypeConverter
//		byte[] secretkeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
//
//		// signing key 생성
//		// SecretKeySpec = 자바에서 제공하는 암호화 키를 만드는 객체
//		Key signingkey = new SecretKeySpec(secretkeyBytes, signatureAlgorithm.getJcaName());
//
//		// 생성한 key 리턴
//		return Jwts.builder().setSubject(subject) // 유저 아이디
//				.signWith(signingkey, signatureAlgorithm) // key와 알고리즘
//				.setExpiration(new Date(System.currentTimeMillis() + expTime)) // 현재 시간설정
//				.compact(); // build 마무리
//	}

	// 토큰을 boolean 으로 검증
	// 아래 로직에서는 subject 값을 확인하기 위해 String 설정
//	public String getSubjectMethod(String token) {
//		// plyload에 key, value 지정
//		Claims claims = Jwts.parserBuilder().setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY)).build()
//				.parseClaimsJws(token) // 토큰을 넣어 디코딩
//				.getBody(); // 데이터를 body로 받는다. (claim이 만들어진다)
//
//		return claims.getSubject(); // 토큰의 subject 값을 가져온다. (= 유저아이디)
//	}

	// JWT 조회
//	@Override
//	public String getSubject(String token) {
//		String subject = getSubjectMethod(token);
//		return subject;
//	}

	
	
} // end
