package com.khlopin.BearSWB.controllers;

import com.khlopin.BearSWB.entity.User;
import com.khlopin.BearSWB.services.ChatRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friend")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.POST, RequestMethod.OPTIONS, RequestMethod.GET})
public class AddFriendController {

    @PostMapping
    public void addFriend(@RequestParam("loginOfOwner") String loginOfOwner, @RequestParam("loginOfAddingUser") String loginOfAddingUser) {
        ChatRepository.addFriend(loginOfOwner,loginOfAddingUser);
    }

    @GetMapping
    public List<User> getFriendList(@RequestParam("login") String login) {
        return ChatRepository.getFriendListByUserLogin(login);
    }

}
