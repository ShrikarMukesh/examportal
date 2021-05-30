package com.exam.controller;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    //creating user
    @PostMapping("/")
    public User createUser(@RequestBody User user) throws Exception {

        user.setProfile("default.png");
        Set<UserRole> roles = new HashSet<>();

        Role role = new Role();
        role.setRoleId(45L);
        role.setRoleName("NORMAL");

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        roles.add(userRole);
        return this.userService.createUser(user,roles);
    }

    //Getting a User
    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username){
       return this.userService.getUser(username);
    }

    //Delete User
    @DeleteMapping("/userId")
    public void deleteUser(@PathVariable("userId") Long userId){
         this.userService.deleteUser(userId);
    }

    //Delete User
    @GetMapping("/users")
    public List<User> userList(){
         return this.userService.listOfUsers();
    }
}
