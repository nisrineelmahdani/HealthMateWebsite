package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements  UserService {

    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
 public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder){
     super();
     this.userRepository = userRepository;
     this.passwordEncoder = passwordEncoder;
 }
    @Override
    public User save(UserRegistrationDto registrationDto) {
       User user = new User(registrationDto.getFirstName(), registrationDto.getLastName() , registrationDto.getEmail() ,  passwordEncoder.encode(registrationDto.getPassword()), Arrays.asList(new Role("ROLE_USER")));
       return  userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }

        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            throw new IllegalArgumentException("L'utilisateur n'a aucun rôle assigné !");
        }

        return new org.springframework.security.core.userdetails.User( user.getEmail() ,  user.getPassword(),mapRolesToAuthorities(user.getRoles()));

    }
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toList());
    }


    @Override
    public boolean authenticate(String email, String password) {
        // Look up user by email
        User user = userRepository.findByEmail(email);
        if (user != null) {
            // Check if the provided password matches the stored encrypted password
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }

}
