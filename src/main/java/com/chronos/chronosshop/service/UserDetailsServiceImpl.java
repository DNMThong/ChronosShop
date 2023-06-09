package com.chronos.chronosshop.service;

import com.chronos.chronosshop.entity.Admin;
import com.chronos.chronosshop.entity.Users;
import com.chronos.chronosshop.repository.AdminRepository;
import com.chronos.chronosshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Users> user = userRepository.findByEmail(email);
        Optional<Admin> admin = adminRepository.findByEmail(email);
        if(user.isPresent()) {
            return user.get();
        }else if(admin.isPresent()) {
            return admin.get();
        }

        throw new UsernameNotFoundException("User not available");
    }
}
