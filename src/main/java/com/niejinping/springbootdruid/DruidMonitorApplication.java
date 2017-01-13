package com.niejinping.springbootdruid;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@MapperScan({ "com.niejinping.springbootdruid.mapper" })  // 注意，在这里扫包。application.properties设置好像不行
public class DruidMonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(DruidMonitorApplication.class, args);
	}
}
