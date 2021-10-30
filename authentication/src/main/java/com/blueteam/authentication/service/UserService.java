package com.blueteam.authentication.service;

import com.blueteam.authentication.entity.Role;
import com.blueteam.authentication.entity.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);
    User getUser(String username);
    Role saveRole(Role role);
    List<User> getUsers();
    void addRoleToUser(String username, String rolename);
}
