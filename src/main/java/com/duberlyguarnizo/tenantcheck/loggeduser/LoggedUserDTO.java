package com.duberlyguarnizo.tenantcheck.loggeduser;

import lombok.Data;

@Data
public class LoggedUserDTO {
    public String name;
    public String lastname;
    public String username;
    public String email;
}
