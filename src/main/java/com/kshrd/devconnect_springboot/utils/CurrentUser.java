package com.kshrd.devconnect_springboot.utils;

import com.kshrd.devconnect_springboot.model.entity.AppUser;
import org.springframework.security.core.context.SecurityContextHolder;

public class CurrentUser {
    public static AppUser appUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

}
