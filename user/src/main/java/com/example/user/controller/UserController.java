package com.example.user.controller;

import com.example.user.model.User;
import com.example.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping ("/registerUser")
    public ResponseEntity<User> registerUser(@RequestBody User User){
        User registerUser = userService.registerUser(User);
        return new ResponseEntity<>(registerUser, HttpStatus.CREATED);
    }
    @GetMapping ("/getUser/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id){
        User User = userService.getUserById(id);
        return ResponseEntity.ok(User);
    }
    @DeleteMapping("/deleteUser/{id}")
    public void delete(@PathVariable Long id){
        userService.deleteById(id);
    }
    @PutMapping("/updateUser/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User User){
        User.setUserId(id);
        return userService.update(id, User);
    }
}
