package com.decode.tinder.ai.backend.conversations;
import java.time.LocalDateTime;
//import org.bson.codecs.jsr310.LocalDateTimeCodec;

public record ChatMessages(
    String messageText,
    String authorId,
    LocalDateTime messageTime
) {

}
