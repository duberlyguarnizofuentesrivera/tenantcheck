package com.duberlyguarnizo.tenantcheck.security;

import com.duberlyguarnizo.tenantcheck.loggeduser.LoggedUser;
import com.duberlyguarnizo.tenantcheck.loggeduser.LoggedUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class LoggedUserLoader implements UserDetailsService {
    LoggedUserRepository loggedUserRepository;

    public LoggedUserLoader(LoggedUserRepository loggedUserRepository) {
        this.loggedUserRepository = loggedUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoggedUser user = loggedUserRepository.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("Username not found in our database!");
        }
        return new User(
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                user.isAccountNonExpired(),
                user.isCredentialsNonExpired(),
                user.isAccountNonLocked(),
                user.getAuthorities()
        );
    }
}
