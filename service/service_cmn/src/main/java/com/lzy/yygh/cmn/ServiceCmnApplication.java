package com.lzy.yygh.cmn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.lzy"})
public class ServiceCmnApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServiceCmnApplication.class, args);
	}
}
