package com.khlopin.BearSWB.services;

import com.google.gson.Gson;
import com.khlopin.BearSWB.entity.Access;
import com.khlopin.BearSWB.entity.User;


public class CreateAccountService {

    private final Gson gson = new Gson();

    private final InitDefaultAvatarService initDefaultAvatarService = new InitDefaultAvatarService();

    public Access getAccessForCreateAccount(String dataFromFront) {
        User clientUser = gson.fromJson(dataFromFront, User.class);
        if (ChatRepository.findUserFromDbByLogin(clientUser.getLogin()) == null) {
            int hash = clientUser.getPassword().hashCode();
            clientUser.setPassword(String.valueOf(hash));
            clientUser.setAvatar(initDefaultAvatarService.initAvatar());
            ChatRepository.addUserInDB(clientUser);
            return Access.TRUE;
        }
        return Access.FALSE;
    }
}
