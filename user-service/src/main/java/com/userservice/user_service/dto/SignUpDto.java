package com.userservice.user_service.dto;

import com.userservice.user_service.entity.Role;
import lombok.Data;

@Data
public class SignUpDto {

    private String name;
    private String email;
    private String password;
    private Role role;
}
