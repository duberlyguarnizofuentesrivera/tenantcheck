package com.duberlyguarnizo.tenantcheck.loggeduser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminController {
    LoggedUserService userService;
    LoggedUserDTOMapper dtoMapper;
    LoggedUserCreationDTOMapper creationDTOMapper;

    public AdminController(LoggedUserService userService, LoggedUserDTOMapper dtoMapper, LoggedUserCreationDTOMapper creationDTOMapper) {
        this.userService = userService;
        this.dtoMapper = dtoMapper;
        this.creationDTOMapper = creationDTOMapper;
    }

    @GetMapping("/control-panel")
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
            return "redirect:/login";
        }
        return "admin/control-panel";
    }

    @GetMapping("/admin/create-user")
    public String createUserWithPrivilegesPage(@RequestParam(required = false) String created, Model model) {
        model.addAttribute("userDTO", new LoggedUserCreationDTO());
        UserRole[] roles = UserRole.values();
        model.addAttribute("roles", roles);
        model.addAttribute("created", created != null);
        return "admin/create-user";
    }

    @PostMapping("/admin/create-user-action")
    public String createUserWithPrivilegesAction(@ModelAttribute LoggedUserCreationDTO userCreationDTO) {
        log.warn("USER CREATION: component of receivedDTO:" + userCreationDTO.getEmail());
        LoggedUser user = creationDTOMapper.loggedUserCreationDTOToLoggedUser(userCreationDTO);
        log.warn("USER CREATION: component of mappedUser:" + user.getEmail());
        boolean creationResult = userService.save(user);
        if (creationResult) {
            return "redirect:admin/admin-create-user?created=true";
        } else {
            return "redirect:admin/admin-create-user?created=false";
        }
    }

    @GetMapping("/list")
    public List<LoggedUserDTO> listAll() {
        return userService.getAll()
                .stream()
                .map(dtoMapper::loggedUserToLoggedUserDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/list/super-level")
    public List<LoggedUserDTO> listAllButAdmins() {
        return userService.getAllNotAdmin()
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

    @GetMapping("/list/admins")
    public List<LoggedUserDTO> listAdmin() {
        return userService.getAllAdminUsers()
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
