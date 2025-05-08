	package com.raffenio;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.raffenio.model.TwitterAccount;
import com.raffenio.service.FileManager;
import com.raffenio.service.FileManagerNoCloseable;

import lombok.Cleanup;
import lombok.val;

@SpringBootApplication
public class LombokSpringExampleApplication {

	private static final Logger log= LoggerFactory.getLogger(LombokSpringExampleApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(LombokSpringExampleApplication.class, args);
		val();
		var();
		nonNull();
		cleanUp();
		
	}
		

	/**
	 * val is a Lombok constant basically
	 */
	private static void val() {
		log.info("---------------------------------");
		log.info("val example");
		log.info("-----------");
		
		final String username1="@raffenio"; 
		log.info("username : {} ",username1); // username : @raffenio  
		
		//username1="@raffenio"; this fails as this is a final string
		
		val username="@raffenio";  //lombok constant
		log.info("username : {} ",username); // username : @raffenio 
		//username=""; this fails as this is a lombok constant
		
		log.info("username : {} has this class: {} ",username,username.getClass().getName());  //  username : @raffenio has this class: java.lang.String 
		log.info("---------------------------------");
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
		log.info("");
		log.info("var example");
		log.info("-----------");
		
		var profileDescription="Geek, developer and cool";  //lombok variable
		log.info("profileDescription : {} of type of : {}",profileDescription,profileDescription.getClass().getName()); // profileDescription : Geek, developer and cool of type of : java.lang.String
		
		profileDescription="And i like Dragon ball";  //lombok variable, this is permitted
		log.info("profileDescription : {} of type of : {}",profileDescription,profileDescription.getClass().getName()); // profileDescription : And i like Dragon ball of type of : java.lang.String
		
		//This will cause and issue as the type is not a string
		//profileDescription=new ArrayList<>(); 
		log.info("---------------------------------");
	}

	
	/**
	 * NonNull example
	 * 
	 * This is a non-nullable field, if the field is null it will throw an exception
	 * @see  https://projectlombok.org/features/NonNull
	 */
	private static void nonNull() {
		log.info("");
		log.info("nonNull example");
		log.info("---------------");
		
		TwitterAccount twitterAccount=new TwitterAccount("Mintakastar"); 
		log.info("twitterAccount : {}",twitterAccount.getUsername()); //  twitterAccount : Mintakastar
		
		//This will cause an issue as the username is null
		try {
			TwitterAccount twitterAccountNull=new TwitterAccount(null); ////Exception in thread "main" java.lang.NullPointerException: username is marked non-null but is null
		} catch (Exception e) {
			log.error("Error: {}",e.getMessage()); //Error: username is marked non-null but is null
		}
		
		
		
		log.info("---------------------------------");
	}
	
	private static void cleanUp() {
		log.info("");
		log.info("cleanUp example");
		log.info("---------------");
		
		//(1) try catch cleanUp example
		log.info("");
		log.info("3-Try catch cleanUp example");
		//if i do not close this, it will not release the resources, and a warning will be shown "Resource leak: 'writer' is never closed"
		FileManager writerTry=new FileManager(); 
		writerTry.write("Hi Raffenio Friends!", "friends.txt");
		
		try {
			//this will release the resources
			//but we need to handle the exception
			writerTry.close();
		} catch (IOException e) {
			log.error("Error: {}",e.getMessage()); 
		}

		//(2) Using try with resources
		log.info("");
		log.info("3-Try with resources cleanUp example");
		try (FileManager writerTryWR = new FileManager();) {
			writerTryWR.write("Hi Raffenio Friends!", "friends.txt");
			
			//IMPORTANT NOTE: try with resources will automatically close the resources
		} catch (IOException e) {
			log.error("Error: {}", e.getMessage());
		}
		
		// (3) Using lombok
		log.info("");
		log.info("3-Lombok cleanUp example");
		try{
			@Cleanup FileManager writerLombok = new FileManager();  //IMPORTANT LOMBOK DOESNO NEED TO USE CLOASEABLE INTERFACE, WE CAN SEND A METHOD NAME
			writerLombok.write("Hi Raffenio Friends!", "friends.txt");

			// IMPORTANT NOTE: lombok will automatically close the resources
		} catch (IOException e) {
			log.error("Error: {}", e.getMessage());
		}

		// (3) Using lombok no closeable 
		log.info("");
		log.info("3.1-Lombok cleanUp example without closeable");
		try {
			//there is a warning that the FileManagerNoCloseable does not have a close method
			@Cleanup("releaseResource")
			FileManagerNoCloseable writerNCLombok = new FileManagerNoCloseable(); // IMPORTANT LOMBOK DOESNO NEED TO USE CLOASEABLE INTERFACE,
															// WE CAN SEND A METHOD NAME
			writerNCLombok.write("Hi Raffenio Friends!", "friends.txt");

			// IMPORTANT NOTE: lombok will automatically close the resources
		} catch (IOException e) {
			log.error("Error: {}", e.getMessage());
		}

				
		log.info("---------------------------------");
	}
}
