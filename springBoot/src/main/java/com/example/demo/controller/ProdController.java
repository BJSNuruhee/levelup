package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TestDto;

@RestController
@RequestMapping("/prod") // prod로 시작하는 클라이언트의 요청을 처리한다.
public class ProdController {
	
	@GetMapping("/get/test/data") 
	public TestDto test(TestDto dto) {
		dto.setName("Prod properties 설정");
		return dto;
	};
	
	@PostMapping("/post/test/data")
	public String postData(@RequestBody TestDto dto) {
		System.out.println("Received data: " + dto.getName());
		return "전송 받기 성공";
	}
}











//////////////////////////////////////////////////////////////////
/* MVC 과정
1. Client가 httpRequest를 내장 톰캣을 거쳐 DispatcherServlet에게 요청한다

2. DispatcherServlet은 HandlerMapping와 통신하여 요청받은 request를 처리할
    controller가 있는지 확인한다. -> 있으면 적합한 controller를 return 해준다.

3. DispatcherServlet이 return받은 controller에게 처리 요청을 한다.
    controller는 요청을 처리하고 Model and View를 return 한다.
    
4. DispatcherServlet은 return 받은 view를 찾아달라고 viewResolver에게 요청한다.
    viewResolver가 알맞은 view를 return 한다.

5. DispatcherServlet은 View를 요청해 실행한다.
        이때 view에서 model 객체를 사용할 수 있게 한다. 

6. DispatcherServlet이 response를 다시 내장 톰캣을 거쳐 Client에게 보낸다.
         결과 화면을 return
     response : 1xx, 2xx, 3xx, 4xx, 5xx

------------------------------------------------------------

DispatcherServlet 이란?
- dispatch는 보내다라는 뜻이다.

- httpRequest를 가장 먼저 받아 적합한 controller에 위임해주는 front controller다 

- 과거에는 web.xml에 URL를 모두 맵핑하였지만, 현재는 DispatcherServlet이 처리


- Maven Dependencies -> spring-webmvc-jar에 존재

--------------------------------------------------------------

viewResolver 란?
- 실행할 view를 찾는 일을 한다.

-페이지 컨트롤러가 리턴한 view 이름에 해당하는 view component를 찾는 역할

*/