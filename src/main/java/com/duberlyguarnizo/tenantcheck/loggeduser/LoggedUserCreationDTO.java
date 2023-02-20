package com.duberlyguarnizo.tenantcheck.loggeduser;

import lombok.Data;

import java.util.List;

@Data
public class LoggedUserCreationDTO {
    private String name;
    private String lastname;
    private String username;
    private String password;
    private String email;
    private List<String> roles;
}
