package com.khlopin.BearSWB.controllers.userControllers;

import com.khlopin.BearSWB.entity.User;
import com.khlopin.BearSWB.services.ChatRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userPage")

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET}, exposedHeaders = "Access-Control-Allow-Origin")
public class UserPageController {

    @GetMapping("/{id}")
    @CrossOrigin("*")
    public User getUser(@PathVariable String id) {
        return ChatRepository.findUserFromDbByID(Integer.parseInt(id));
    }

}

