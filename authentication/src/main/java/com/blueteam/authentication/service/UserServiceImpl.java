package com.blueteam.authentication.service;

import com.blueteam.authentication.controller.NotificationClient;
import com.blueteam.authentication.dto.NotificationEmailDTO;
import com.blueteam.authentication.dto.UserDTO;
import com.blueteam.authentication.entity.Role;
import com.blueteam.authentication.entity.User;
import com.blueteam.authentication.exceptions.user.EmailAlreadyExistsException;
import com.blueteam.authentication.repo.OffsetBasedPageRequest;
import com.blueteam.authentication.repo.RoleRepository;
import com.blueteam.authentication.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        return userRepository.save(user);
    }

    @Override
    public User getUser(String username) {

        return userRepository.findByUsername(username);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(rolename);
        user.getRoles().add(role);

    }

    @Override
    public List<User> getAllUsers(int limit, int offset) {
        Pageable pageable = (Pageable) new OffsetBasedPageRequest(limit, offset);
        return userRepository.findAll((org.springframework.data.domain.Pageable) pageable).getContent();
    }

    @Override
    public User registerNewUserAccount(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()) != null) {
            throw new EmailAlreadyExistsException(userDTO.getEmail());
        }

        User user = new User();
        Role role = new Role();
        role.setName("USER");
        user.setName(userDTO.getName());
        user.setUsername(userDTO.getUsername());
        user.setLastName(userDTO.getLastName());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());
        user.setEnabled(false);
        user.setRoles(Arrays.asList(role));
        Random rand = new Random();
        String code = RandomString.make(4);
        user.setRandomValue(code);
        User userSaved = userRepository.save(user);
        notify(userSaved);

        return userSaved;
    }

    private void notify(User user) {
        NotificationEmailDTO emailDTO = new NotificationEmailDTO();
        emailDTO.setEmail(user.getEmail());
        emailDTO.setVerificationCode(user.getRandomValue());
        NotificationClient.sendNotificationEmail(emailDTO);
    }

    public boolean verify(String verificationCode) {
        User user = userRepository.findByRandomValue(verificationCode);

        if (user == null || user.isEnabled()) {
            return false;
        } else {
            user.setRandomValue(null);
            user.setEnabled(true);
            userRepository.save(user);

            return true;
        }

    }

}
