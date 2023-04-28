package com.khlopin.BearSWB.controllers.chatControllers.chatForAll;


import com.khlopin.BearSWB.entity.MessageForAll;
import com.khlopin.BearSWB.services.repositories.ChatRepository;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
public class ConversationHistory {


    private final ChatRepository chatRepository = new ChatRepository();


   @GetMapping("/history")
   @CrossOrigin("*")
   @SneakyThrows
    protected List<MessageForAll> sendHistory() {
       return chatRepository.getMessageList();
    }



}
