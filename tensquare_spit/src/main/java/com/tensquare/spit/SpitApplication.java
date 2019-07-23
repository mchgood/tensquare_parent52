package com.tensquare.spit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * @auther: tangkc
 * @Date: 2019/7/23 21:49
 * @Description:
 */
@SpringBootApplication
public class SpitApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpitApplication.class,args);
	}

	@Bean
	public IdWorker idWorkker(){
		return new IdWorker(1, 1);
	}
}
