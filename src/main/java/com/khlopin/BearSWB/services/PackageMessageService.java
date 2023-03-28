package com.khlopin.BearSWB.services;

import com.google.gson.Gson;
import com.khlopin.BearSWB.entity.Message;
import com.khlopin.BearSWB.entity.PackageMessage;

public class PackageMessageService {

    private final Gson gson = new Gson();

    public PackageMessage getPackageMessageFromJson(String str) {
        return gson.fromJson(str, PackageMessage.class);
    }

    public Message unPackage(PackageMessage packageMessage) {
        return Message.builder()
                .ownerOfMessage(ChatRepository.findUserFromDbByLogin(packageMessage.getName()))
                .text(packageMessage.getMessage())
                .build();
    }
}
