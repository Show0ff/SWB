package com.khlopin.BearSWB.controllers.userControllers;

import com.khlopin.BearSWB.entity.User;
import com.khlopin.BearSWB.services.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/personalPage")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
public class PersonalUserPageController {

    private final UserRepository userRepository = new UserRepository();

    @PostMapping  //TODO need to change on get method
    public User getUser(@RequestBody String dataFromFront) {
        return userRepository.getUserFromDbByLogin(dataFromFront);
    }

}
