package com.khlopin.BearSWB.controllers.chatControllers;


import com.khlopin.BearSWB.entity.Message;
import com.khlopin.BearSWB.services.ChatRepository;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
public class ConversationHistory {


   @GetMapping("/history")
   @CrossOrigin("*")
   @SneakyThrows
    protected List<Message> sendHistory() {
       return ChatRepository.getMessageList();
    }



}
