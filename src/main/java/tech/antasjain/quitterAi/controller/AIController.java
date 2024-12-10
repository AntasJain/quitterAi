package tech.antasjain.quitterAi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class AIController {
   private final ChatClient chatClient;

   @QueryMapping
   String completion(@Argument String message){
      return chatClient.prompt().system("You are an expert in Addiction recoveries, you can be asked tips to quit addictions, cravings related to it, milestones and stages").user(message).call().content();
   }
}
