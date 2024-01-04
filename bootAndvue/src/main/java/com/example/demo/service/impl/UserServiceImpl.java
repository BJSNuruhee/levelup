package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDto;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.JwtService;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	JwtService jwtService;
	
	// 유저 리스트 조회
	@Override
	public List<UserDto> findAll() {
		List<UserDto> list = userRepository.findAll();
		return list;
	}

	// 회원가입
	@Override
	public String postSignUp(UserDto vo) {
		List<UserDto> list = userRepository.findAll();
		try {
			if(list.stream().noneMatch(d -> 
				d.getUserSeq().equals(vo.getUserId()) ||
				d.getUserId().equals(vo.getUserId()) ||
				d.getUserToken().equals(vo.getUserToken()))) {
				String token = jwtService.getToken("id", vo.getUserId());
				vo.setUserToken(token);
				userRepository.postSignUp(vo);
				System.out.println("회원가입 성공");
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("회원가입 실패");
			e.printStackTrace();
			return "fail";
		} 
		return "success";
	}
	
	// 유저 아이디 조회
	@Override
	public List<String> getUserId() {
		List<String> list = userRepository.getUserId();
		return list;
	}

}
