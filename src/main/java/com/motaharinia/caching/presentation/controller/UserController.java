package com.motaharinia.caching.presentation.controller;

import com.motaharinia.caching.business.UserService;
import com.motaharinia.caching.presentation.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public UserModel create(@RequestBody @Validated  UserModel userModel) {
        return userService.create(userModel);
    }

    @GetMapping("/user/{id}")
    public UserModel findOne(@PathVariable Integer id) {
        return userService.findOne(id);
    }

    @GetMapping("/user")
    public List<UserModel> findAll() {
        return userService.findAll();
    }

    @PutMapping("/user")
    public UserModel update(@RequestBody @Validated  UserModel userModel) {
        return userService.update(userModel);
    }

    @DeleteMapping("/user/{id}")
    public UserModel delete(@PathVariable Integer id) {
        return userService.delete(id);
    }
}
