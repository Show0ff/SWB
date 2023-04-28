package com.khlopin.BearSWB.controllers.chatControllers.personalChat;

import com.khlopin.BearSWB.entity.PersonalMessage;
import com.khlopin.BearSWB.entity.User;
import com.khlopin.BearSWB.services.repositories.PersonalChatRepository;
import com.khlopin.BearSWB.services.repositories.UserRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class PersonalConversationHistory {

    private final UserRepository userRepository = new UserRepository();
    private final PersonalChatRepository personalChatRepository = new PersonalChatRepository();


    @GetMapping("/privateHistory/{id}")
    @CrossOrigin("*")
    protected List<PersonalMessage> sendHistory(@PathVariable Long id) {
        User userFromDbByID = userRepository.getUserFromDbByID(id);
        return personalChatRepository.getPersonalChatHistory(userFromDbByID);
    }

}
