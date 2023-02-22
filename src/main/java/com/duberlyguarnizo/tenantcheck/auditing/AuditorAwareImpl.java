package com.duberlyguarnizo.tenantcheck.auditing;

import com.duberlyguarnizo.tenantcheck.loggeduser.LoggedUser;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
        LoggedUser principal = (LoggedUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        return Optional.of(principal.getId());
    }
}
