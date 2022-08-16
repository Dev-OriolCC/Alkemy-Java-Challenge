package com.example.disney.auth.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class UserDto {

    @Email(message = "Must be an email")
    private String username;
    @Size(min = 8)
    private String password;

}
