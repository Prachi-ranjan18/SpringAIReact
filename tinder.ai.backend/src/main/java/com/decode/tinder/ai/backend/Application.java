package com.decode.tinder.ai.backend;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.decode.tinder.ai.backend.conversations.ChatMessages;
import com.decode.tinder.ai.backend.conversations.Conversation;
import com.decode.tinder.ai.backend.conversations.ConversationRepository;
import com.decode.tinder.ai.backend.profiles.Gender;
import com.decode.tinder.ai.backend.profiles.Profile;
import com.decode.tinder.ai.backend.profiles.ProfileRepository;
;

@SpringBootApplication
public class Application implements CommandLineRunner{
	@Autowired 
		private ProfileRepository profileRepository;
	@Autowired
	private ConversationRepository conversationRepository;

	public static void main(String[] args) {
		
		SpringApplication.run(Application.class, args);
	}
	public void run(String... args){
		profileRepository.deleteAll();
		conversationRepository.deleteAll();
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

			Profile profile2 = new Profile(
				"2", 
				"Prachi", 
				"Ranjan", 
				20, 
				"Indian", 
				Gender.FEMALE,
				"I like to code ", 
				"pic1.jpg", 
				"INTP");
			profileRepository.save(profile);
			profileRepository.save(profile2);
			profileRepository.findAll().forEach(System.out :: println);
			Conversation conversation = new Conversation(
				"1", profile.id(), List.of(
					new ChatMessages(
					"Hello", 
					profile.id(), 
					LocalDateTime.now())
				)
				);
			conversationRepository.save(conversation);
			conversationRepository.findAll().forEach(System.out :: println);
	}

}
