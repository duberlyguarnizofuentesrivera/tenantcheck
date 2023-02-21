package com.duberlyguarnizo.tenantcheck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc //TODO: delete this line and try /admin and /super
public class TenantCheckApplication {

    public static void main(String[] args) {
        SpringApplication.run(TenantCheckApplication.class, args);
    }

}
