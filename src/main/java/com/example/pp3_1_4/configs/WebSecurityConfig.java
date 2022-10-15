package com.example.pp3_1_4.configs;


import com.example.pp3_1_4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private final SuccessUserHandler successUserHandler;
    private final UserService userService;
    private  final BCryptPasswordEncoder passwordEncoder;



    public WebSecurityConfig(SuccessUserHandler successUserHandler, UserService userService,BCryptPasswordEncoder passwordEncoder) {
        this.successUserHandler = successUserHandler;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable().antMatcher("/**")
                .authorizeRequests()

                .antMatchers("/admin/allUsers").hasRole("ADMIN")
                .antMatchers("/user/oneUser").hasAnyRole("ADMIN", "USER")
                .antMatchers("/", "/login/**").permitAll()
                .antMatchers("/api/allUsers").hasRole("ADMIN")
                .antMatchers("/api/oneUser").hasAnyRole("ADMIN", "USER")

                .anyRequest().authenticated()

                .and()
                .formLogin().permitAll().successHandler(successUserHandler)

                .and()
                .logout()

                .permitAll()
                .and()
                .httpBasic();
        return http.build();
    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setUserDetailsService(userService);
        return authenticationProvider;
    }
    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}