package com.khlopin.BearSWB.controllers.userControllers;

import com.khlopin.BearSWB.entity.User;
import com.khlopin.BearSWB.services.repositories.UserRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("user/avatar")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})

public class AvatarController {

    private final UserRepository userRepository = new UserRepository();


    @PostMapping("/{login}")
    public ResponseEntity<Void> uploadAvatar(@PathVariable String login,
                                             @RequestPart("file") MultipartFile file) throws IOException {
        User user = userRepository.getUserFromDbByLogin(login);
        user.setAvatar(file.getBytes());
        userRepository.updateUser(user);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/{login}")
    public ResponseEntity<byte[]> getAvatar(@PathVariable String login) {
        User user = userRepository.getUserFromDbByLogin(login);
        byte[] avatar = user.getAvatar();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(avatar.length);
        return new ResponseEntity<>(avatar, headers, HttpStatus.OK);
    }


}
