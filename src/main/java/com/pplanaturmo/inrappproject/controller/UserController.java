package com.pplanaturmo.inrappproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pplanaturmo.inrappproject.dto.CreateUserRequest;
import com.pplanaturmo.inrappproject.model.User;
import com.pplanaturmo.inrappproject.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    
    @Autowired
    private UserService userService;

    // @PostMapping
    // public User createUser(@Valid @RequestBody User user) {
    //     return userService.createUser(user);
    // }

    @PostMapping
    public User createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        User user = convertToUser(createUserRequest);
        return userService.createUser(user);
    }

    private User convertToUser(CreateUserRequest createUserRequest) {
        User user = new User();
        
        user.setName(createUserRequest.getName());
        user.setSurname(createUserRequest.getSurname());
        user.setIdCard(createUserRequest.getIdCard());
        user.setHealthCard(createUserRequest.getHealthCard());
        user.setEmail(createUserRequest.getEmail());
        user.setPhone(createUserRequest.getPhone());
        user.setDataConsent(createUserRequest.getDataConsent());
        user.setPassword(createUserRequest.getPassword()); 
        return user;
    }


    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {

        user.setId(id); 
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/department/{departmentId}")
    public List<User> getUsersByDepartmentId(@PathVariable Long departmentId) {
        return userService.getUsersByDepartmentId(departmentId);
    }


}
