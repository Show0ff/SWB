package com.khlopin.BearSWB.controllers.userControllers;

import com.khlopin.BearSWB.entity.User;
import com.khlopin.BearSWB.services.ChatRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/allUsers")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET})
public class AllUsersPage {
    @GetMapping
    public List<User> getAllUsers() {
        return ChatRepository.getAllUsers();
    }
}
