package com.khlopin.BearSWB.services;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
public class InitDefaultAvatarService {


    @SneakyThrows
    public byte[] initAvatar() {
        return Files.readAllBytes(Path.of("src\\main\\resources\\basicPhoto.jpg"));
    }


}
