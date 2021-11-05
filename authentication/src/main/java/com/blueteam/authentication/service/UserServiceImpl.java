package com.blueteam.authentication.service;

import com.blueteam.authentication.dto.UserDTO;
import com.blueteam.authentication.entity.Role;
import com.blueteam.authentication.entity.User;
import com.blueteam.authentication.exceptions.user.EmailAlreadyExistsException;
import com.blueteam.authentication.repo.OffsetBasedPageRequest;
import com.blueteam.authentication.repo.RoleRepository;
import com.blueteam.authentication.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.UnknownServiceException;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;
import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User getUser(String username) {

        return userRepository.findByUsername(username);
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
    public User registerNewUserAccount(UserDTO userDTO)  {
        if (userRepository.findByEmail(userDTO.getEmail())!=null) {
            throw new EmailAlreadyExistsException(userDTO.getEmail());
//            try {
//                throw new InstanceAlreadyExistsException("There is an account with that email address: "
//                        + userDTO.getEmail());
//            } catch (InstanceAlreadyExistsException e) {
//                e.printStackTrace();
//            }

        }

        User user = new User();
        Role role = new Role();
        role.setName("USER");
        user.setName(userDTO.getName());
        user.setLastName(userDTO.getLastName());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setRoles(Arrays.asList(role));

        return userRepository.save(user);
    }
//    private boolean emailExists(String email) {
//        return userRepository.findByEmail(email) != null;
//    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username);
//        if (user == null)
//            throw new UsernameNotFoundException("This username doesn't exist");
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        user.getRoles().forEach(role->{authorities.add(new SimpleGrantedAuthority(role.getName()));
//        });
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
//    }
}
