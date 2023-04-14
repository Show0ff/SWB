package com.khlopin.BearSWB.controllers.authControllers;

import com.google.gson.Gson;

import com.khlopin.BearSWB.entity.Access;
import com.khlopin.BearSWB.services.LoginInAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.POST, RequestMethod.OPTIONS})
public class AuthController {

    private final Gson gson = new Gson();
    private final LoginInAccountService loginInAccountService = new LoginInAccountService();

    @PostMapping
    public ResponseEntity<String> login(@RequestBody String dataFromFront) {
        Access access = loginInAccountService.getAccess(dataFromFront);
        return ResponseEntity.ok(gson.toJson(access));
    }
}

