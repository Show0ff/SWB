package com.khlopin.BearSWB.services;

import com.khlopin.BearSWB.entity.Chat;
import com.khlopin.BearSWB.entity.PersonalMessage;
import com.khlopin.BearSWB.entity.User;
import com.khlopin.BearSWB.services.repositories.PersonalChatRepository;

import java.util.List;

public class PersonalChatService {

    private final PersonalChatRepository personalChatRepository = new PersonalChatRepository();

    public Chat startChat(User first, User second) {
        Chat chat = new Chat(List.of(first, second));
        first.getChats().add(chat);
        second.getChats().add(chat);
// TODO создаётся по два чата в таблице
        return chat;
    }
    public void addMessageToUserList(PersonalMessage personalMessage) {
        User sender = personalMessage.getSender();
        User receiver = personalMessage.getReceiver();
        Chat chatBetweenUsers = personalChatRepository.findChatBetweenUsers(sender.getId(), receiver.getId());
        chatBetweenUsers.getMessages().add(personalMessage);
    }
}
