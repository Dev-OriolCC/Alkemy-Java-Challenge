package com.example.disney.auth.service;

import com.example.disney.auth.dto.AuthResponse;
import com.example.disney.auth.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public void createUser(UserDto userDto);

}
