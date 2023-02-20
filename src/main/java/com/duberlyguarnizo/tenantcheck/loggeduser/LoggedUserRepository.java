package com.duberlyguarnizo.tenantcheck.loggeduser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggedUserRepository extends JpaRepository<LoggedUser, Long> {
    LoggedUser findByUsername(String username);
}
