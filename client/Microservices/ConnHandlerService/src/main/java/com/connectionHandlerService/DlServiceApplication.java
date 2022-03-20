package com.connectionHandlerService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class DlServiceApplication {

	private static ApplicationContext applicationContext;

	// Add bean of webClientBuilder to use Weblient api
	@Bean
	public WebClient.Builder getWebClientBuilder(){
		return WebClient.builder();
	}


	public static void main(String[] args) {

		applicationContext = SpringApplication.run(DlServiceApplication.class, args);
		displayAllBeans();
	}


	public static void displayAllBeans() {
		String[] allBeanNames = applicationContext.getBeanDefinitionNames();
		for (String beanName : allBeanNames) {
			System.out.println(beanName);
		}
	}
}
