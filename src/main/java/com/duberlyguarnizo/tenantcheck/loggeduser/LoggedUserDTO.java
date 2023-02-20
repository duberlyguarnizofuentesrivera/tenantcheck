package com.duberlyguarnizo.tenantcheck.loggeduser;

import lombok.Data;

@Data
public class LoggedUserDTO {
    private String name;
    private String lastname;
    private String username;
    private String email;
}
