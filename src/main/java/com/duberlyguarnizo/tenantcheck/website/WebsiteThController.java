package com.duberlyguarnizo.tenantcheck.website;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class WebsiteThController {
    @GetMapping("/")
    public String indexPage() {
        return "index";
    }
}
