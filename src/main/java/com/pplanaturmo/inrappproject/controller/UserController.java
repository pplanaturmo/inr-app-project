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

import com.pplanaturmo.inrappproject.dto.UserRequest;
import com.pplanaturmo.inrappproject.dto.UpdateUserDepartment;
import com.pplanaturmo.inrappproject.dto.UpdateUserSupervisor;
import com.pplanaturmo.inrappproject.model.User;
import com.pplanaturmo.inrappproject.service.UserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/user")
public class UserController {

    
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public User createUser(@Valid @RequestBody UserRequest createUserRequest) {
        User user = convertToUser(createUserRequest);
        return userService.createUser(user);
    }

    private User convertToUser(UserRequest createUserRequest) {
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


    @GetMapping("/")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable("userId") @Valid @NotNull Long userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable("userId") @Valid @NotNull Long userId ,@Valid @RequestBody UserRequest createUserRequest) {

        User user = convertToUser(createUserRequest);
        user.setId(null);
          return userService.updateUser(user);
    }

    @PutMapping("/{userId}/department")
    public User assignDepartmentToUser(@PathVariable("userId") @Valid @NotNull Long userId, @RequestBody @Valid UpdateUserDepartment updateUserDepartment) {
        Long departmentId = updateUserDepartment.getDepartmentId();
        return userService.assignDepartmentToUser(userId, departmentId);
    }

    @PutMapping("/{userId}/supervisor")
    public User assignSupervisorToUser(@PathVariable("userId") @Valid @NotNull Long userId, @RequestBody @Valid UpdateUserSupervisor updateUserSupervisor) {
        Long professionalId = updateUserSupervisor.getProfessionalId();
        return userService.assignDepartmentToUser(userId, professionalId);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@Valid @PathVariable("userId") @NotNull Long userId) {
        userService.deleteUser(userId);
    }


    @GetMapping("/department/{departmentId}")
    public List<User> getUsersByDepartmentId(@Valid @PathVariable("userId") @NotNull Long departmentId) {
        return userService.getUsersByDepartmentId(departmentId);
    }


}
