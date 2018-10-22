package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

@SpringBootApplication
@Slf4j
@EnableAsync
public class DemoApplication {

//	@RestController
//	public static class MyController {
//		@GetMapping("/callable")
//		public Callable<String> callable() {
//			log.info("callable");
//			return () -> {
//				log.info("async");
//				Thread.sleep(2000);
//				return "hello";
//			};
//		}
//	}


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
