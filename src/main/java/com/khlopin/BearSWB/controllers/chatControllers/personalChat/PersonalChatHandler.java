package com.khlopin.BearSWB.controllers.chatControllers.personalChat;


import com.khlopin.BearSWB.entity.PackageMessage;

import com.khlopin.BearSWB.entity.PersonalMessage;
import com.khlopin.BearSWB.services.PackageMessageService;
import com.khlopin.BearSWB.services.PersonalChatService;
import com.khlopin.BearSWB.services.repositories.PersonalChatRepository;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;


public class PersonalChatHandler extends TextWebSocketHandler {

    private final List<WebSocketSession> webSocketSessionList = new ArrayList<>();

    private final PackageMessageService packageMessageService = new PackageMessageService();

    private final PersonalChatService personalChatService = new PersonalChatService();
    private final PersonalChatRepository personalChatRepository = new PersonalChatRepository();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        webSocketSessionList.add(session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        for (WebSocketSession webSocketSession : webSocketSessionList) {
            webSocketSession.sendMessage(message);
        }
        PackageMessage packageMessageFromJson = packageMessageService.getPackageMessageFromJson(new String(message.asBytes()));
        PersonalMessage personalMessage = packageMessageService.unPackagePersonalMessage(packageMessageFromJson);
        if (personalChatRepository.isChatExistByUserIds(personalMessage.getSender().getId(), personalMessage.getReceiver().getId())) {
            personalChatRepository.addPersonalMessage(personalMessage);
//            ?Repository.updateMessagesInPersonalChat(personalMessage); //TODO Нужно добавить сообщение в коллекцию messages в чате и обновить это в БД
        } else {
            personalChatService.startChat(personalMessage.getSender(), personalMessage.getReceiver());
            personalChatRepository.addPersonalMessage(personalMessage);
//            ?Repository.updateMessagesInPersonalChat(personalMessage);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        webSocketSessionList.remove(session);
    }


}
