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
		var() ;
		
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
	
	
	/**
	 * var is a Lombok variable basically
	 * 
	 * java 10 introduced var keyword which is similar to var
	 * 
	 * var example
	 *
     * this is basically variables with no type defined, it will auto detect 
     * the type and will assign it to the variable field
	 */
	private static void var() {

		var profileDescription="Geek, developer and cool";  //lombok variable
		log.info("profileDescription : {} of type of : {}",profileDescription,profileDescription.getClass().getName()); // profileDescription : Geek, developer and cool of type of : java.lang.String
		
		profileDescription="And i like Dragon ball";  //lombok variable, this is permitted
		log.info("profileDescription : {} of type of : {}",profileDescription,profileDescription.getClass().getName()); // profileDescription : And i like Dragon ball of type of : java.lang.String
		
		//This will cause and issue as the type is not a string
		//profileDescription=new ArrayList<>(); 
		
	}

}
