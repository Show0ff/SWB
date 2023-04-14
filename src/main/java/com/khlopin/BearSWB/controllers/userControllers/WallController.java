package com.khlopin.BearSWB.controllers.userControllers;

import com.khlopin.BearSWB.entity.Post;
import com.khlopin.BearSWB.entity.Wall;
import com.khlopin.BearSWB.services.ChatRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wall")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
public class WallController {

    @GetMapping
    public Wall getWallOfUser(@RequestParam("login") String login) {
        Wall wallByLogin = ChatRepository.getWallByLogin(login);

        System.out.println(wallByLogin + "FFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
        return wallByLogin;
    }


    @PostMapping("/addPost")
    public Post addPost(@RequestBody Post post, @RequestParam("login") String login) {
        System.out.println(post);
        ChatRepository.addPostInUserWall(login, post);
        return post;
    }
}
