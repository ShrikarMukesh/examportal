package com.exam.service;

import com.exam.model.User;
import com.exam.model.UserRole;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Component
public interface UserService {
    //creating user
    public User createUser(User user, Set<UserRole> userRoles) throws Exception;

    //get user by username
    public User getUser(String username);

    //delete user by Id
    public void deleteUser(Long userId);

    public List<User> listOfUsers();
}
