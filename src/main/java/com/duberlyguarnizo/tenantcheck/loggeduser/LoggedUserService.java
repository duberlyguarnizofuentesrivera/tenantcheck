package com.duberlyguarnizo.tenantcheck.loggeduser;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoggedUserService {
    private final LoggedUserRepository repository;
    private final PasswordEncoder pwdEncoder;
//    private final

    public LoggedUserService(LoggedUserRepository repository, PasswordEncoder pwdEncoder) {
        this.repository = repository;
        this.pwdEncoder = pwdEncoder;
    }

    public List<LoggedUser> getAll() {
        return repository.findAll();
    }

    public List<LoggedUser> getAllNotAdmin() {
        List<UserRole> roles = List.of(UserRole.USER, UserRole.PAID_USER, UserRole.SUPER);
        return repository.findByRolesIn(roles);
    }

    public List<LoggedUser> getAllNotSuper() {
        List<UserRole> roles = List.of(UserRole.USER, UserRole.PAID_USER);
        return repository.findByRolesIn(roles);
    }

    public List<LoggedUser> getAllPaidUsers() {
        List<UserRole> roles = List.of(UserRole.PAID_USER);
        return repository.findByRolesIn(roles);
    }

    public List<LoggedUser> getAllRegularUsers() {
        List<UserRole> roles = List.of(UserRole.USER);
        return repository.findByRolesIn(roles);
    }

    public List<LoggedUser> getAllSuperUsers() {
        List<UserRole> roles = List.of(UserRole.SUPER);
        return repository.findByRolesIn(roles);
    }

    public List<LoggedUser> getAllAdminUsers() {
        List<UserRole> roles = List.of(UserRole.ADMIN);
        return repository.findByRolesIn(roles);
    }

    public boolean save(LoggedUser user) {
        LoggedUser tempUser = repository.findByUsername(user.getUsername());
        LoggedUser tempUser2 = repository.findByEmail(user.getEmail());
        if (tempUser == null && tempUser2 == null) {
            user.setPassword(pwdEncoder.encode(user.getPassword()));
            repository.save(user);
            return true;
        }
        return false;
    }

    public LoggedUser getByName(String currentUserName) {
        return repository.findByUsername(currentUserName);
    }
}
