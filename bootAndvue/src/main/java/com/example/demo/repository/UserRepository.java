package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.UserDto;

@Mapper
public interface UserRepository {

	public List<UserDto> findAll();
	public void postSignUp(UserDto vo);
	public List<String> getUserId();
	public UserDto memLogin(UserDto vo); // 로그인 체크
	public String findUser(String id); // 로그인 체크
	public UserDto findUserDetail(String id);
}
