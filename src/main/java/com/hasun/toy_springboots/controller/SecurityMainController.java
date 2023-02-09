package com.hasun.toy_springboots.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SecurityMainController {
    @GetMapping({"/"})
    public ModelAndView main(ModelAndView modelAndView){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            System.out.println(((UserDetails)principal).getUsername());
            System.out.println(((UserDetails)principal).getPassword());
            System.out.println(((UserDetails)principal).getAuthorities());
            System.out.println(((UserDetails)principal).isAccountNonExpired());
            System.out.println(((UserDetails)principal).isAccountNonLocked());
            System.out.println(((UserDetails)principal).isCredentialsNonExpired());
            System.out.println(((UserDetails)principal).isEnabled());
        } else {
            String username = principal.toString();
        }
        String viewName = "/security/main";
        modelAndView.setViewName(viewName);
        return modelAndView;
    }
}
