package ru.myproject.first_project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import ru.myproject.first_project.security.authentication.OtpAuthenticationProvider;
import ru.myproject.first_project.security.authentication.UsernamePasswordAuthenticationProvider;
import ru.myproject.first_project.security.authentication.filters.InitialAuthenticationFilter;
import ru.myproject.first_project.security.authentication.filters.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final OtpAuthenticationProvider otpAuthenticationProvider;
    private final UsernamePasswordAuthenticationProvider
            usernamePasswordAuthenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    public SecurityConfig(OtpAuthenticationProvider otpAuthenticationProvider, UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.otpAuthenticationProvider = otpAuthenticationProvider;
        this.usernamePasswordAuthenticationProvider = usernamePasswordAuthenticationProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(otpAuthenticationProvider).
                authenticationProvider(usernamePasswordAuthenticationProvider);
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
    @Bean
    public InitialAuthenticationFilter initialAuthenticationFilter() throws Exception {
        return new InitialAuthenticationFilter(authenticationManager());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.addFilterBefore(initialAuthenticationFilter(), BasicAuthenticationFilter.class)
        .addFilterAfter(jwtAuthenticationFilter, BasicAuthenticationFilter.class);
        http.authorizeRequests().
                mvcMatchers(HttpMethod.GET, "/login", "/css/**", "/fonts/**").permitAll().mvcMatchers(HttpMethod.POST, "/login").permitAll();
        http.authorizeRequests().anyRequest().authenticated();
    }
}
