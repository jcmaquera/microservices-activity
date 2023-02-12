package com.example.user.service;

import com.example.user.model.User;
import com.example.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        return userRepository.save(user);
    }
    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }
    public User getUserById(Long userId) {
        return userRepository.findById(userId).get();
    }
    public User update(Long userId, User user) {
        user.setUserId(userId);
        return userRepository.save(user);
    }



}


