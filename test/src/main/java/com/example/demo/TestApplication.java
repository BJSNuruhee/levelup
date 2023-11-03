package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

}

/* @SpringBootApplication
- @Target
  - Java Compiler가 어노테이션이 어디에 적용될지 결정하기 위해 사용된다.
  
- @Retention
  - 해당 어노테이션이 실제로 적용되고 유지되는 범위를 나타낸다.
  
- @Documented
  - 클래스가 문서화될 경우 해당 어노테이션이 적용되었음을 명시하도록 한다.
  
- @Inherited
  - 자식 클래스가 부모에 선언된 어노테이션을 같이 사용하고 싶을 때 선언한다.
  
- @SpringBootConfiguration
  - 애플리케이션의 구성을 제공한다.
  
- @ComponentScan
  - @component 어노테이션 및 @Service, @Repository, @Controller 등의 어노테이션을 스캔하여 Bean으로 등록해준다.
  
- @EnableAutoConfiguration
  - 사전에 정의한 라이브러리들을  Bean으로 등록
  - 사전 정의 파일 위치 : Dependencies > spring-boot-autoconfigure > META-INF > spring.factories  
 
-----------------------------------------------------------------------------------------

- @SpringBootAplication 어노테이션으로 인해 스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성이 모두 자동으로 설정된다.

- @SpringBootAplication 어노테이션이 있는 위치부터 설정을 읽어가기 때문에 이 어노테이션을 포함한 클래스는 항상 프로젝트의 최상단에 위치해야만 한다.
  - 자바 main 메서드는 어플리케이션이 시작되면 가장 먼저 실행된다.
  
summary

최상위에서 실행되어 spring에 설정된 정보들을 Bean으로 등록해주기 때문에 서버 실행 시 스프링부트가 정상 실행이 가능한 것
  
*/
