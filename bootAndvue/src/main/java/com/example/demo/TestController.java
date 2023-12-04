package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class TestController {
		
	@RequestMapping(value="/index")
	public String index() {
		Logger logger = LoggerFactory.getLogger(this.getClass());
		logger.info("hello");
		logger.info("This is logger");
		
		return "hello";
	}
	
}
