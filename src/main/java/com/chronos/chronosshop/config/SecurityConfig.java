package com.chronos.chronosshop.config;

import com.chronos.chronosshop.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableAutoConfiguration
public class SecurityConfig {

    @Bean
    public UserDetailsService getUserDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider getDaoAuthProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(getUserDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(getPasswordEncoder());

        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(auth -> {
//                auth.requestMatchers("/account/sign-up").permitAll()
//                    .requestMatchers("/account/login").permitAll()
//                    .requestMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN")
//                    .requestMatchers("/account/**").hasAnyAuthority("ROLE_USER","OIDC_USER")
//                    .anyRequest().permitAll();
//            })
//            .formLogin()
//                .loginPage("/account/login")
//                .loginProcessingUrl("/account/login")
//                .defaultSuccessUrl("/")
//                .and()
//            .oauth2Login()
//                .defaultSuccessUrl("/account/social?success=true")
//                .and()
//            .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/account/login")
//                .and()
//            .exceptionHandling()
//                .accessDeniedHandler((request,response,accessDeniedException) -> response.sendRedirect("/"))
//                .and()
//            .csrf().disable();
        return http.build();
    }

}
