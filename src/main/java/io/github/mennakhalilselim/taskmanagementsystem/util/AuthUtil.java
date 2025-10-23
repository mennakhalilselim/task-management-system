package io.github.mennakhalilselim.taskmanagementsystem.util;

import io.github.mennakhalilselim.taskmanagementsystem.model.entity.User;
import io.github.mennakhalilselim.taskmanagementsystem.security.model.SecurityUser;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;

@UtilityClass
public class AuthUtil {
    public static User extractUserFromAuthentication(Authentication authentication) {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        return securityUser.getUser();
    }
}
