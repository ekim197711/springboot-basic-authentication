package com.codeinvestigator.basicauthentication.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class MyCustomAuthConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        String passwordencodedMike  = passwordEncoder().encode("mike123");
        String passwordencodedSusanne  = passwordEncoder().encode("susanne123");
        auth.inMemoryAuthentication()
                .withUser("mike").password(passwordencodedMike).roles("DEVELOPER")
                .and()
                .withUser("susanne").password(passwordencodedSusanne).roles("UX");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/nosecurity/**").permitAll()
                .antMatchers("/developer/**").hasRole("DEVELOPER")
                .antMatchers("/uxdesigner/**").hasRole("UX")
                .and().httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
