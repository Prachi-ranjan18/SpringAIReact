package com.decode.tinder.ai.backend.conversations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.decode.tinder.ai.backend.profiles.ProfileRepository;

@RestController
public class ConversationController {
    private final ConversationRepository conversationRepository;
    private final ProfileRepository profileRepository;
    

    public ConversationController(ConversationRepository conversationRepository,ProfileRepository profileRepository) {
        this.conversationRepository = conversationRepository;
        this.profileRepository = profileRepository;
    }

    @PostMapping("/conversation") // conversation will be happening with the AI profile 
    public Conversation createNewConverstation(@RequestBody CreateConversationRequest request){
        profileRepository.findById(request.profileId()).
        orElseThrow(()-> new ResponseStatusException(
            HttpStatus.NOT_FOUND, 
            "Unable to find ProfileId"+
            request.profileId()
        ));
        Conversation conversation = new Conversation(
           UUID.randomUUID().toString(),
            request.profileId(), 
            new ArrayList<>()
            );
        conversationRepository.save(conversation);
        return conversation;
    }

    @PostMapping("/conversation/{conversationId}")
    public Conversation addMessageToConversation(
        @PathVariable String conversationId, 
        @RequestBody ChatMessages chatMessages
    ){
        Conversation conversation = conversationRepository.findById(conversationId).orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Conversation doesn't exist"+
            conversationId
        ));

        profileRepository.findById(chatMessages.authorId()).
        orElseThrow(()-> new ResponseStatusException(
            HttpStatus.NOT_FOUND, 
            "Unable to find ProfileId"+
            chatMessages.authorId()
        ));
        //TODO : Need to validate that the author of a messageText happens to be only the profile associated 
        //with the message user
        ChatMessages chatMessagesTime = new ChatMessages(
            chatMessages.messageText(), chatMessages.authorId() , LocalDateTime.now()
        );
        conversation.messages().add(chatMessagesTime);
        conversationRepository.save(conversation);
        return conversation;
    }
    @GetMapping("/conversation/{conversationId}")
    public Conversation getConversation(
        @PathVariable String conversationId
    ){
        Conversation conversation = conversationRepository
        .findById(conversationId).orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "COnversation ID not found "+ conversationId
        ))  ;
        return conversation;
    }
    public record CreateConversationRequest(
        String profileId
    ){}
}
