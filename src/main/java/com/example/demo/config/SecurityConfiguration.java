package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.addFilterBefore(new CustomFilter(), ChannelProcessingFilter.class);
        httpSecurity.authorizeRequests()
                .antMatchers("/**")
                .permitAll()
                .anyRequest()
                //.permitAll()
                .fullyAuthenticated()
                .and().httpBasic().and().csrf().disable();

    }

    /*
     @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    // roles admin allow to access /admin/**
    // roles user allow to access /user/**
    // custom 403 access denied handler
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http .csrf()
                .disable()
                .authorizeRequests()
                    .antMatchers("/", "/home", "/about").permitAll()
                    .antMatchers("/admin/**").hasAnyRole("ADMIN")
                    .antMatchers("/user/**").hasAnyRole("USER")
                    .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                .and()
                .logout()
                    .permitAll()
                .and()
                .exceptionHandling()
                    .accessDeniedHandler(accessDeniedHandler);
    }

    // create two users, admin and user
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER")
                .and()
                .withUser("admin").password("password").roles("ADMIN");
    }
     */

}