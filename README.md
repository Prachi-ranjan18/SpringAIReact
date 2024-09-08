# SpringAIReact



1. React
2. Springboot java
3. mongodb
4. OpenAI
5. local LLM (Ollama)
6. Stable difussion (Local version of midJourney and dall-e)


#### API which we need : ######
GET /Profile/random -> Pull up random profile from mongodb
Post /conversations -> create a new conversations with a profile (param - profile id )
Post /conversations/ id  -> send a new message to a conversation (response - return a conversation with ai response)
GET / conversation -> get all conversations by the user 
GET /conversation /id -> Get all messages of a conversation
DELETE /conversation/id -> remove conversation and all messages 

##### Mongodb schema ###
collections (Profiles || conversations )
Profiles{ 
    id
    name
    age 
    ethnicity 
    gender
    bio
    image url
    Myers-briggs type
}

conversation{
    id
    profile id 
    messages - list
}
message{
    messageText
    datetime
    profile id - user/ai
}
