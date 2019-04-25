package com.wgu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2 //接口文档
@SpringBootApplication
@Slf4j
public class OaCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(OaCenterApplication.class, args);
		log.info("接口文档 本地：http://localhost:9090/oa/swagger-ui.html#/");
	}

}
