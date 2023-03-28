package com.khlopin.BearSWB.controllers;

import com.google.gson.Gson;
import com.khlopin.BearSWB.entity.Access;
import com.khlopin.BearSWB.services.CreateAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/register")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.POST, RequestMethod.OPTIONS})
public class CreateAccountController {

    private final Gson gson = new Gson();
    private final CreateAccountService createAccountService = new CreateAccountService();

    @PostMapping
    public ResponseEntity<String> register(@RequestBody String dataFromFront) {
        Access accessForCreateAccount = createAccountService.getAccessForCreateAccount(dataFromFront);
        return ResponseEntity.ok(gson.toJson(accessForCreateAccount));
    }

}
