package com.oodles;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@SpringBootApplication
@RestController
@EnableJpaAuditing

public class BlockchainServiceApplication {
	@Value("${spring.application.name}")
	private String name;
	
	public static void main(String[] args) {
		
		SpringApplication.run(BlockchainServiceApplication.class, args);
	}
	@Bean  
	public  AcceptHeaderLocaleResolver localeResolver()  
	{  
	AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();  
	localeResolver.setDefaultLocale(Locale.US);  
	return localeResolver;  
	}  
	//configuring ResourceBundle  
	@Bean  
	public ResourceBundleMessageSource messageSource()  
	{  
	ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();  
	messageSource.setBasename("messages");  
	return messageSource;  
	}  
}
