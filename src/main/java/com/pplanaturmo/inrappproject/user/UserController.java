package com.pplanaturmo.inrappproject.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pplanaturmo.inrappproject.user.dtos.UpdateUserDepartment;
import com.pplanaturmo.inrappproject.user.dtos.UpdateUserInrRange;
import com.pplanaturmo.inrappproject.user.dtos.UpdateUserPattern;
import com.pplanaturmo.inrappproject.user.dtos.UpdateUserSupervisor;
import com.pplanaturmo.inrappproject.user.dtos.UserRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@Tag(name = "User Management", description = "Operations related to user management")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    @Operation(summary = "Create a new user", description = "This endpoint allows you to create a new user by providing the necessary details.", responses = {
            @ApiResponse(responseCode = "201", description = "User created successfully", content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })

    public User createUser(
            @Parameter(description = "Details of the user to be created", required = true) @Valid @RequestBody UserRequest createUserRequest) {
        User user = userService.convertToUser(createUserRequest);

        return userService.createUser(user);
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
    public User updateUser(@PathVariable("userId") @Valid @NotNull Long userId,
            @Valid @RequestBody UserRequest createUserRequest) {

        User user = userService.convertToUser(createUserRequest);
        user.setId(userId);
        return userService.updateUser(user);
    }

    @PutMapping("/{userId}/department")
    public User assignDepartmentToUser(@PathVariable("userId") @Valid @NotNull Long userId,
            @RequestBody @Valid UpdateUserDepartment updateUserDepartment) {
        Long departmentId = updateUserDepartment.getDepartmentId();
        return userService.assignDepartmentToUser(userId, departmentId);
    }

    @PutMapping("/{userId}/supervisor")
    public User assignSupervisorToUser(@PathVariable("userId") @Valid @NotNull Long userId,
            @RequestBody @Valid UpdateUserSupervisor updateUserSupervisor) {
        Long professionalId = updateUserSupervisor.getProfessionalId();
        return userService.assignSupervisorToUser(userId, professionalId);
    }

    @PutMapping("/{userId}/range-inr")
    public User set(@PathVariable("userId") @Valid @NotNull Long userId,
            @RequestBody @Valid UpdateUserInrRange updateUserInrRange) {
        Long rangeId = updateUserInrRange.getRangeId();
        return userService.assignDepartmentToUser(userId, rangeId);
    }

    @PutMapping("/{userId}/dose-pattern")
    public User set(@PathVariable("userId") @Valid @NotNull Long userId,
            @RequestBody @Valid UpdateUserPattern updateUserPattern) {
        Long patternId = updateUserPattern.getPatternId();
        return userService.assignDepartmentToUser(userId, patternId);
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
