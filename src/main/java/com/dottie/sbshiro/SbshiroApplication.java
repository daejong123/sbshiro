package com.dottie.sbshiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.dottie.sbshiro.mapper")
@EnableScheduling//增加支持定时任务的注解
public class SbshiroApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbshiroApplication.class, args);
	}
}
