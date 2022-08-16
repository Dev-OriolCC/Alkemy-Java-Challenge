package com.example.disney.auth.controller;

import com.example.disney.auth.dto.AuthRequest;
import com.example.disney.auth.dto.AuthResponse;
import com.example.disney.auth.dto.UserDto;
import com.example.disney.auth.service.JwtUtils;
import com.example.disney.auth.service.UserDetailsCustomService;
import com.example.disney.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsCustomService userDetailsCustomService;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody UserDto userDto) throws Exception {
        userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(),
                            authRequest.getPassword()
                    )
            );
        } catch ( BadCredentialsException e) {
            // Validation...
            throw new Exception("Wrong credentials", e);
        }
        final UserDetails userDetails = userDetailsCustomService.loadUserByUsername(authRequest.getUsername());
        final String token = jwtUtils.generateToken(userDetails);
        return new AuthResponse(token);
    }

}
