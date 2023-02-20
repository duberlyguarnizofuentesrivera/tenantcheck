package com.duberlyguarnizo.tenantcheck.loggeduser;

import org.springframework.stereotype.Service;

@Service
public class LoggedUserService {
    private final LoggedUserRepository repository;

    public LoggedUserService(LoggedUserRepository repository) {
        this.repository = repository;
    }

    void save(LoggedUser user) {
        repository.save(user);
    }
}
