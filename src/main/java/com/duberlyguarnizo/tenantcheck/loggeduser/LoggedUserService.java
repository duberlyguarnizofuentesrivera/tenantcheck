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

    public void save(LoggedUser user) {
        user.setPassword(pwdEncoder.encode(user.getPassword()));
        repository.save(user);
    }

    public LoggedUser getByName(String currentUserName) {
        return repository.findByUsername(currentUserName);
    }
}
