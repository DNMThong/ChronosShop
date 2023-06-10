package com.chronos.chronosshop.auth;

import com.chronos.chronosshop.entity.Users;
import com.chronos.chronosshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
public class Auth {
    @Autowired
    private UserRepository userRepository;

    public Users getUserLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof Users) {
                return (Users) principal;
            }else if(principal instanceof OAuth2User) {
                OAuth2User oAuth2User = (OAuth2User) principal;
                Map<String,Object> map =  oAuth2User.getAttributes();
                Optional<Users> user = userRepository.findById(String.valueOf(map.get("sub")));
                return user.orElse(null);
            }
        }
        return null;
    }
}
