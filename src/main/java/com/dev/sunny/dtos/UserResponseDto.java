package com.dev.sunny.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserResponseDto {
    private String name;
    private String email;
    private List<RolesDto> roles;
    private boolean isEmailVerified;
}
