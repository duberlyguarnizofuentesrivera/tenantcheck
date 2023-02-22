package com.duberlyguarnizo.tenantcheck.loggeduser;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LoggedUserDTO {
    private String name;
    private String lastname;
    private String username;
    private String email;
    private LocalDateTime lastModifiedDate;
}
