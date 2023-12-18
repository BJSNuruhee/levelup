package com.example.demo.jwt;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {
	// 비밀키 생성
	private static final String SECRET_KEY = "asafdasdfajijoijfiajoijawoiejfiojijojiefiowjef";
	
	// 토큰 구현 메서드
	public String createToken(String subject, long expTime) {
		if(expTime <= 0) {
			throw new RuntimeException("만료 시간은 0보다 커야함");
		}
		
		// 시그니처 알고리즘 선택
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		
		// 스트링 형태의 key를 바이트 단위의 key로 변환
		// jaxb-api : DatatypeConverter
		byte[] secretkeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
		
		// signing key 생성
		// SecretKeySpec = 자바에서 제공하는 암호화 키를 만드는 객체
		Key signingkey = new SecretKeySpec(secretkeyBytes, signatureAlgorithm.getJcaName());
		
		// 생성한 key 리턴
		return Jwts.builder()
				.setSubject(subject) // 유저 아이디
				.signWith(signingkey, signatureAlgorithm) // key와 알고리즘
				.setExpiration(new Date(System.currentTimeMillis() + expTime)) // 현재 시간설정
				.compact(); // build 마무리 
	}
	
	// 토큰을 boolean 으로 검증
	// 아래 로직에서는 subject 값을 확인하기 위해 String 설정
	public String getSubject(String token) {
		// plyload에 key, value 지정
		Claims claims = Jwts.parserBuilder()
				.setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
				.build()
				.parseClaimsJws(token) // 토큰을 넣어 디코딩
				.getBody(); // 데이터를 body로 받는다. (claim이 만들어진다)
				
		return claims.getSubject(); // 토큰의 subject 값을 가져온다. (= 유저아이디)
	}
	
}
