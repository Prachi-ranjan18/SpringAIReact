package com.decode.tinder.ai.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.decode.tinder.ai.backend.profiles.Gender;
import com.decode.tinder.ai.backend.profiles.Profile;
import com.decode.tinder.ai.backend.profiles.ProfileRepository;

@SpringBootApplication
public class Application implements CommandLineRunner{
	@Autowired 
		private ProfileRepository profileRepository;

	public static void main(String[] args) {
		
		SpringApplication.run(Application.class, args);
	}
	public void run(String... args){
		//System.out.println("Welcome to my app");
		Profile profile = new Profile(
			"1", 
			"Kaushik", 
			"Bisht", 
			38, 
			"Indian", 
			Gender.MALE,
			"Eat ,Code ,Sleep ", 
			"pic1.jpg", 
			"INTP");
			profileRepository.save(profile);
			profileRepository.findAll().forEach(System.out :: println);
	}

}
