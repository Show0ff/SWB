package com.khlopin.BearSWB.controllers.userControllers;

import com.khlopin.BearSWB.entity.Post;
import com.khlopin.BearSWB.entity.Wall;
import com.khlopin.BearSWB.services.repositories.UserPageRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wall")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
public class WallController {

    private final UserPageRepository userPageRepository = new UserPageRepository();

    @GetMapping
    public Wall getWallOfUser(@RequestParam("login") String login) {
        return userPageRepository.getWallByLogin(login);
    }


    @PostMapping("/addPost")
    public Post addPost(@RequestBody Post post, @RequestParam("login") String login) {
        userPageRepository.addPostInUserWall(login, post);
        return post;
    }
}
