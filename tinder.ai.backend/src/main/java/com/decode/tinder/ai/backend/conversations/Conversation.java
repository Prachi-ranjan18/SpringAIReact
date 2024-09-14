package com.decode.tinder.ai.backend.conversations;
import java.util.*;
public record Conversation(
    String id,
    String profileId,
    List<ChatMessages> messages
) {

} 
