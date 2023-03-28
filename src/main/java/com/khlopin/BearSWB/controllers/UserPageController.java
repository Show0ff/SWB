package com.khlopin.BearSWB.controllers;

import com.khlopin.BearSWB.entity.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/personalPage")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET,RequestMethod.POST, RequestMethod.OPTIONS})
public class UserPageController {
@GetMapping
    public void printUser(User user) {

    }
}
