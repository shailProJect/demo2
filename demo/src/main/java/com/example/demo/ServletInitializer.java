package com.example.demo;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

import com.sun.glass.ui.Application;

@Configuration
public class ServletInitializer extends SpringBootServletInitializer {
	public ServletInitializer() {
		super();
		setRegisterErrorPageFilter(false);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

}
