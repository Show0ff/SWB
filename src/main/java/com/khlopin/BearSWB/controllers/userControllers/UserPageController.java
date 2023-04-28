package com.khlopin.BearSWB.controllers.userControllers;

import com.khlopin.BearSWB.entity.User;
import com.khlopin.BearSWB.services.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userPage")

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET}, exposedHeaders = "Access-Control-Allow-Origin")
public class UserPageController {

    private final UserRepository userRepository = new UserRepository();

    @GetMapping("/{id}")
    @CrossOrigin("*")
    public User getUser(@PathVariable String id) {
        return userRepository.getUserFromDbByID(Integer.parseInt(id));
    }

}

