package com.finalproject.orca_spring_finalproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.finalproject.entity.Application;

@SpringBootApplication
@EntityScan(basePackages = {"com.finalproject.entity"})
@MapperScan(basePackages = {"com.finalproject.DJ.mapper",
					 "com.finalproject.JH.mapper",
					 "com.finalproject.JW.mapper",
					 "com.finalproject.JE.mapper",
					 "com.finalproject.SG.mapper"})
@ComponentScan(basePackages = {"com.finalproject.DJ.controller","com.finalproject.DJ.service", "com.finalproject.DJ.restcontroller", "com.finalproject.common","com.finalproject.config","com.finalproject.DJ.scheduler",
							   "com.finalproject.SG.controller","com.finalproject.SG.service","com.finalproject.SG.restcontroller",
							   "com.finalproject.JE.controller","com.finalproject.JE.service","com.finalproject.JE.restcontroller",
							   "com.finalproject.JW.controller","com.finalproject.JW.service",
							   "com.finalproject.JH.controller","com.finalproject.JH.service", "com.finalproject.JH.config","com.finalproject.JH.filter"}) // 컨트롤러 위치 및 서비스 설정
@EnableScheduling // 스케줄링 사용
@EnableJpaRepositories(basePackages = {"com.finalproject.DJ.repository"})//저장소 위치
public class OrcaSpringFinalprojectApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder applaction) {
		return applaction.sources(OrcaSpringFinalprojectApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(OrcaSpringFinalprojectApplication.class, args);
	}

}
