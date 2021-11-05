package com.blueteam.authentication.service;

import com.blueteam.authentication.dto.UserDTO;
import com.blueteam.authentication.entity.Role;
import com.blueteam.authentication.entity.User;
import com.blueteam.authentication.repo.OffsetBasedPageRequest;
import com.blueteam.authentication.repo.UserRepository;
import org.springframework.data.domain.Pageable;

import javax.management.InstanceAlreadyExistsException;
import java.util.List;

public interface UserService {

    User saveUser(User user);
    User getUser(String username);
    Role saveRole(Role role);
    List<User> getUsers();
    void addRoleToUser(String username, String rolename);
    public List<User> getAllUsers(int limit, int offset);
    public User registerNewUserAccount(UserDTO user) throws InstanceAlreadyExistsException;
}
