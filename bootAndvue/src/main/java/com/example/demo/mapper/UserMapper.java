package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.entity.User;

@Mapper
public interface UserMapper {

	public List<User> getLists();
	public void postSignUp(User vo);
	public List<String> getUserId();
	public User memLogin(User vo); // 로그인 체크
}
