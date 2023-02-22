package com.duberlyguarnizo.tenantcheck.loggeduser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/user")
public class UserController {
    LoggedUserService userService;
    LoggedUserCreationDTOMapper creationDTOMapper;
    LoggedUserDTOMapper dtoMapper;

    public UserController(LoggedUserService userService,
                          LoggedUserCreationDTOMapper creationDTOMapper,
                          LoggedUserDTOMapper dtoMapper) {
        this.userService = userService;
        this.creationDTOMapper = creationDTOMapper;
        this.dtoMapper = dtoMapper;
    }

    @PostMapping("/create")
    public void create(@RequestBody LoggedUserCreationDTO creationDTO) {
        LoggedUser user = creationDTOMapper.loggedUserCreationDTOToLoggedUser(creationDTO);
        userService.save(user);
    }







}
