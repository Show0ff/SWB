package com.khlopin.BearSWB.services;

import com.google.gson.Gson;
import com.khlopin.BearSWB.entity.MessageForAll;
import com.khlopin.BearSWB.entity.PackageMessage;
import com.khlopin.BearSWB.entity.PersonalMessage;
import com.khlopin.BearSWB.services.repositories.UserRepository;

public class PackageMessageService {

    private final Gson gson = new Gson();

    private final UserRepository userRepository = new UserRepository();

    public PackageMessage getPackageMessageFromJson(String str) {
        return gson.fromJson(str, PackageMessage.class);
    }

    public MessageForAll unPackageMessageForAll(PackageMessage packageMessage) {
        return MessageForAll.builder()
                .ownerOfMessage(userRepository.getUserFromDbByLogin(packageMessage.getOwnerOfMessage().getLogin()))
                .text(packageMessage.getText())
                .build();
    }

    public PersonalMessage unPackagePersonalMessage(PackageMessage packageMessage) {
        return PersonalMessage.builder()
                .sender(userRepository.getUserFromDbByLogin(packageMessage.getOwnerOfMessage().getLogin()))
                .receiver(userRepository.getUserFromDbByLogin(packageMessage.getReceiverOfMessage()))
                .text(packageMessage.getText())
                .build();
    }


}
