package com.decode.tinder.ai.backend.profiles;

public record Profile(
    String id,
    String firstName,
    String lastName,
    int age,
    String ethinicity,
    Gender gender,
    String bio ,
    String imageUrl,
    String myersBriggsPersonalityType
) {
    
}
