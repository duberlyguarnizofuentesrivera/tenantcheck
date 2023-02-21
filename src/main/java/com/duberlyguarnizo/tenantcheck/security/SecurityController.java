package com.duberlyguarnizo.tenantcheck.security;

import com.duberlyguarnizo.tenantcheck.loggeduser.LoggedUser;
import com.duberlyguarnizo.tenantcheck.loggeduser.LoggedUserService;
import com.duberlyguarnizo.tenantcheck.loggeduser.UserRole;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SecurityController {
    LoggedUserService userService;

    public SecurityController(LoggedUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/create-admin")
    public void createAdmin() {
        LoggedUser admin = new LoggedUser();
        admin.setName("Duberly");
        admin.setUsername("admin");
        admin.setLastname("Guarnizo");
        admin.setPassword("1801");
        admin.setRoles(List.of(UserRole.ADMIN));
        admin.setEmail("duberlygfr@gmail.com");
        admin.setActive(true);
        userService.save(admin);
    }

    @GetMapping("/create-super")
    public void createSuper() {
        LoggedUser superUser = new LoggedUser();
        superUser.setName("Mijail");
        superUser.setLastname("Potorosky");
        superUser.setUsername("super");
        superUser.setPassword("1801");
        superUser.setRoles(List.of(UserRole.SUPER));
        superUser.setEmail("mijail@gmail.com");
        superUser.setActive(true);
        userService.save(superUser);
    }
}
