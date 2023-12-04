package com.example.demo.dto;

public class yejun {
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}

/* 
1. MVC 패턴에서 Model을 담당하고 있는 곳

2. DB와 controller 사이에서 데이터를 전달 -> DTO : Date Transter Object

------------------------------------------------------------
DTO

1. 이름에서 알 수 있듯이 DTO는 데이터 전송을 위해 생성되는 자바 객체(java Beans)이다.

2. 사용 이유 : 데이터를 한 번에 묶어서 필요한 부분만 사용이 가능하다.
       쓰지 않을 경우 멤버 1개마다 각각 요청 및 서버에 저장 필요

*/