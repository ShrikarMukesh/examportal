package com.exam.service.impl;

import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repository.RoleRepository;
import com.exam.repository.UserRepository;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {

        User local = this.userRepository.findByUsername(user.getUsername());
        if(local !=null){
            System.out.println("User already present");
            throw new Exception("User already present");
        }else{

            for(UserRole userRole : userRoles){
                roleRepository.save(userRole.getRole());
            }
            user.getUserRoles().addAll(userRoles);
            local = this.userRepository.save(user);
        }
        return local;
    }
   //getting user by username
    @Override
    public User getUser(String username) {

        return this.userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(Long userId) {
         userRepository.deleteById(userId);
    }

    @Override
    public List<User> listOfUsers() {
        return this.userRepository.findAll();
    }

}
