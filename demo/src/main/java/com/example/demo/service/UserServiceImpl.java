package com.example.demo.service;

import com.example.demo.model.Pharmacy;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.PharmacyRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.web.dto.UserRegistrationDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PharmacyRepository pharmacyRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    private static final List<String> PHARMACY_EMAILS = Arrays.asList(
            "pharmacyNajd@gmail.com"
    );

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PharmacyRepository pharmacyRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.pharmacyRepository = pharmacyRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    @Transactional
    public User save(UserRegistrationDto registrationDto) {
        boolean isPharmacy = PHARMACY_EMAILS.contains(registrationDto.getEmail());
//
//        // Chercher la pharmacie par le nom si l'utilisateur est une pharmacie
//        Pharmacy pharmacy = null;
//        if (isPharmacy && registrationDto.getPharmacyName() != null && !registrationDto.getPharmacyName().isEmpty()) {
//            // Chercher la pharmacie dans la base de données
//            pharmacy = pharmacyRepository.findByName(registrationDto.getPharmacyName())
//                    .orElseThrow(() -> new IllegalArgumentException("Pharmacy not found: " + registrationDto.getPharmacyName()));
//        }

        // Créer l'utilisateur avec les informations fournies
        User user = new User(
                registrationDto.getFirstName(),
                registrationDto.getLastName(),
                registrationDto.getEmail(),
                passwordEncoder.encode(registrationDto.getPassword()),
                Arrays.asList(new Role("ROLE_USER")),
                isPharmacy
                 // Associer la pharmacie à l'utilisateur si elle existe
        );

        // Sauvegarder l'utilisateur dans la base de données
        return userRepository.save(user);
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

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles())
        );
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean authenticate(String email, String password) {
        User user = userRepository.findByEmail(email);
        return user != null && passwordEncoder.matches(password, user.getPassword());
    }

    public Optional<User> login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return Optional.of(user);
        }
        return Optional.empty();
    }
}
