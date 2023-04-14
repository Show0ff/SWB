package com.khlopin.BearSWB.controllers.chatControllers.handler;



import com.khlopin.BearSWB.entity.PackageMessage;
import com.khlopin.BearSWB.services.ChatRepository;
import com.khlopin.BearSWB.services.PackageMessageService;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final List<WebSocketSession> webSocketSessionList = new ArrayList<>();
    private final PackageMessageService packageMessageService = new PackageMessageService();
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        webSocketSessionList.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        for (WebSocketSession webSocketSession : webSocketSessionList) {
            webSocketSession.sendMessage(message);
        }
        PackageMessage packageMessage = packageMessageService.getPackageMessageFromJson(new String(message.asBytes()));
        ChatRepository.addMessageInDB(packageMessageService.unPackage(packageMessage));
    }


    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status){
        webSocketSessionList.remove(session);
    }

}
