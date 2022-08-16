package com.example.disney.auth.service;

import com.example.disney.auth.dto.UserDto;
import com.example.disney.auth.entity.User;
import com.example.disney.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Service
public class UserDetailsCustomService implements UserDetailsService {


    private final UserRepository userRepository;

    @Autowired
    public UserDetailsCustomService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        com.example.disney.auth.entity.User userEntity = userRepository.findByUsername(userName);
        if (userEntity == null) {
            throw new UsernameNotFoundException("Username or password not found");
        }
        //
        userName =  userEntity.getUsername();
        String password = userEntity.getPassword();
        return new org.springframework.security.core.userdetails.User(userName, password, new ArrayList<>());

    }


}
