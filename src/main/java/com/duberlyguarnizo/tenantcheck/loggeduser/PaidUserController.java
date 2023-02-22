package com.duberlyguarnizo.tenantcheck.loggeduser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/premium-user")
public class PaidUserController {
    LoggedUserDTOMapper userDTOMapper;
    LoggedUserService userService;

    public PaidUserController(LoggedUserDTOMapper userDTOMapper, LoggedUserService userService) {
        this.userDTOMapper = userDTOMapper;
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String getProfile(Model model) {
        String currentUserName = SecurityContextHolder
                .getContext()
                .getAuthentication().getName();
        log.warn("current user name: " + currentUserName);
        LoggedUser currentUser = userService.getByName(currentUserName);
        if (currentUser != null) {
            LoggedUserDTO userDTO = userDTOMapper.loggedUserToLoggedUserDTO(currentUser);
            model.addAttribute("user", userDTO);
            return "user/profile";
        } else {
            return "redirect:/login";
        }

    }
}
