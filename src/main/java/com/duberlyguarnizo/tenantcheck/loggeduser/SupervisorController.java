package com.duberlyguarnizo.tenantcheck.loggeduser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/supervisor")
public class SupervisorController {
    LoggedUserService userService;
    LoggedUserDTOMapper dtoMapper;

    public SupervisorController(LoggedUserService userService, LoggedUserDTOMapper dtoMapper) {
        this.userService = userService;
        this.dtoMapper = dtoMapper;
    }

    @GetMapping("/control-panel")
    public String superPanel(Model model) {
        String currentUserName = SecurityContextHolder
                .getContext()
                .getAuthentication().getName();
        LoggedUser currentUser = userService.getByName(currentUserName);
        if (currentUser != null) {
            LoggedUserDTO userDTO = dtoMapper.loggedUserToLoggedUserDTO(currentUser);
            model.addAttribute("currentUser", userDTO);
        } else {
            return "redirect:/login";
        }
        return "supervisor/super-panel";
    }

    @GetMapping("/list/all")
    public List<LoggedUserDTO> listAllButAdmins() {
        return userService.getAllNotAdmin()//Supervisor's "all" are everyone but admins
                .stream()
                .map(dtoMapper::loggedUserToLoggedUserDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/list/user-level")
    public List<LoggedUserDTO> listAllButSuper() {
        return userService.getAllNotSuper()
                .stream()
                .map(dtoMapper::loggedUserToLoggedUserDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/list/supervisors")
    public List<LoggedUserDTO> listSuper() {
        return userService.getAllSuperUsers()
                .stream()
                .map(dtoMapper::loggedUserToLoggedUserDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/list/paid-users")
    public List<LoggedUserDTO> listPaidUsers() {
        return userService.getAllPaidUsers()
                .stream()
                .map(dtoMapper::loggedUserToLoggedUserDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/list/regular-users")
    public List<LoggedUserDTO> listRegularUsers() {
        return userService.getAllRegularUsers()
                .stream()
                .map(dtoMapper::loggedUserToLoggedUserDTO)
                .collect(Collectors.toList());
    }
}
