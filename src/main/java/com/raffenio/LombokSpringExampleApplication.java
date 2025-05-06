	package com.raffenio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.val;

@SpringBootApplication
public class LombokSpringExampleApplication {

	private static final Logger log= LoggerFactory.getLogger(LombokSpringExampleApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(LombokSpringExampleApplication.class, args);
		val() ;
		
	}
	
	/**
	 * val is a Lombok constant basically
	 */
	private static void val() {

		final String username1="@raffenio"; 
		log.info("username : {} ",username1); // username : @raffenio  
		
		//username1="@raffenio"; this fails as this is a final string
		
		val username="@raffenio";  //lombok constant
		log.info("username : {} ",username); // username : @raffenio 
		//username=""; this fails as this is a lombok constant
		
		log.info("username : {} has this class: {} ",username,username.getClass().getName());  //  username : @raffenio has this class: java.lang.String 
			
	}

}
