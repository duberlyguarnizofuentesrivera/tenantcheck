package com.duberlyguarnizo.tenantcheck.loggeduser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface LoggedUserRepository extends JpaRepository<LoggedUser, Long> {
    LoggedUser findByUsername(String username);

    LoggedUser findByEmail(String email);

    List<LoggedUser> findByRolesIn(Collection<UserRole> role);
}
