package com.crime_management.dto.user;

import lombok.Data;
import java.util.Set;

@Data
public class UserDTO {
    private Long id;
    private String fullName;
    private String email;
    private boolean active;
    private Set<String> roles;
}
