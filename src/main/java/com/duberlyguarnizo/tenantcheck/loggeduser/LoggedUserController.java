package com.duberlyguarnizo.tenantcheck.loggeduser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping("api/v1/logged-user")
public class LoggedUserController {
    LoggedUserService userService;
    LoggedUserCreationDTOMapper creationDTOMapper;
    LoggedUserDTOMapper dtoMapper;

    public LoggedUserController(LoggedUserService userService,
                                LoggedUserCreationDTOMapper creationDTOMapper,
                                LoggedUserDTOMapper dtoMapper) {
        this.userService = userService;
        this.creationDTOMapper = creationDTOMapper;
        this.dtoMapper = dtoMapper;
    }

    @GetMapping("/all")
    public List<LoggedUserDTO> list() {
        return userService.getAll()
                .stream()
                .map(dtoMapper::loggedUserToLoggedUserDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/create")
    public void create(@RequestBody LoggedUserCreationDTO creationDTO) {
        LoggedUser user = creationDTOMapper.loggedUserCreationDTOToLoggedUser(creationDTO);
        userService.save(user);
    }

    @GetMapping("/admin")
    public String adminPanel(Model model) {
        String currentUserName = SecurityContextHolder
                .getContext()
                .getAuthentication().getName();
        log.warn("current user name: " + currentUserName);
        LoggedUser currentUser = userService.getByName(currentUserName);
        if (currentUser != null) {
            log.warn("current user is different from null!");
            LoggedUserDTO userDTO = dtoMapper.loggedUserToLoggedUserDTO(currentUser);
            log.warn("UserDTO is: " + userDTO.getLastname() + ", " + userDTO.getName());
            model.addAttribute("currentUser", userDTO);
        } else {
            log.warn("current user is NULL");
        }
        return "admin-panel";
    }

    @GetMapping("/super")
    public String superPanel(Model model) {
        String currentUserName = SecurityContextHolder
                .getContext()
                .getAuthentication().getName();
        LoggedUser currentUser = userService.getByName(currentUserName);
        if (currentUser != null) {
            LoggedUserDTO userDTO = dtoMapper.loggedUserToLoggedUserDTO(currentUser);
            model.addAttribute("currentUser", userDTO);
        }
        return "super-panel";
    }


}
