package com.greenfoxacademy.pokemon.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SecurityConf extends WebSecurityConfigurerAdapter {

  @Autowired
  public void configureAuth(AuthenticationManagerBuilder auth) throws Exception {
//    auth
//        .inMemoryAuthentication()
//          .withUser("csabi")
//          .password("troy")
//          .roles("ADMIN")
//        .and()
//          .withUser("Edo")
//          .password("roxy")
//          .roles("USER");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .cors().and().csrf().disable()
        .authorizeRequests()
          .antMatchers(HttpMethod.GET, "/").permitAll()
          .antMatchers("/login").permitAll()
          .antMatchers("/index").hasRole("USER")
          .antMatchers("/register").permitAll()
          .antMatchers("/pokos").hasRole("USER")
        .and()
          .formLogin().permitAll()
        .and()
          .logout()
          .logoutSuccessUrl("/login?logout")
          .permitAll();
  }
}
