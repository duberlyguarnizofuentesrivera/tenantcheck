package com.duberlyguarnizo.tenantcheck.loggeduser;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/logged-user")
public class LoggedUserController {
    LoggedUserService userService;
    LoggedUserCreationDTOMapper creationDTOMapper;

    public LoggedUserController(LoggedUserService userService, LoggedUserCreationDTOMapper creationDTOMapper) {
        this.userService = userService;
        this.creationDTOMapper = creationDTOMapper;
    }

    @PostMapping("/create")
    void create(@RequestBody LoggedUserCreationDTO creationDTO) {
        LoggedUser user = creationDTOMapper.loggedUserCreationDTOToLoggedUser(creationDTO);
        userService.save(user);
    }


}
