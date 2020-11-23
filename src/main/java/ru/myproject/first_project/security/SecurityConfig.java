package ru.myproject.first_project.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/**").permitAll();
//        http.csrf().disable().authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/user/**").hasAnyRole("USER", "ADMIN").
//                antMatchers("/css**", "/js**", "/fonts**").permitAll().
//                antMatchers( "/about", "/", "/index").
//                permitAll().anyRequest().authenticated().and().formLogin().
//                loginPage("/enter").loginProcessingUrl("/enter").
//                permitAll().and().logout().logoutUrl("/logout").
//                deleteCookies("JSESSIONID").permitAll();
    }
}
