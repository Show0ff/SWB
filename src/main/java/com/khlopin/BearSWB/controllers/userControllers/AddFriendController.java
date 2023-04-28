package com.khlopin.BearSWB.controllers.userControllers;

import com.khlopin.BearSWB.entity.User;
import com.khlopin.BearSWB.services.repositories.FriendRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friend")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.POST, RequestMethod.OPTIONS, RequestMethod.GET})
public class AddFriendController {

    private final FriendRepository friendRepository = new FriendRepository();

    @PostMapping
    public void addFriend(@RequestParam("loginOfOwner") String loginOfOwner, @RequestParam("loginOfAddingUser") String loginOfAddingUser) {
       friendRepository.addFriend(loginOfOwner,loginOfAddingUser);
    }

    @GetMapping
    public List<User> getFriendList(@RequestParam("login") String login) {
        return friendRepository.getFriendListByUserLogin(login);
    }

}
