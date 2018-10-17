package com.greenfoxacademy.pokemon.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SecurityConf extends WebSecurityConfigurerAdapter {
  private DataSource dataSource;

//  @Override
//  public void configure(WebSecurity web) throws Exception {
//    web
//        .ignoring()
//        .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
//  }

  @Autowired
  public void configureAuth(AuthenticationManagerBuilder auth) throws Exception {
//    auth
//        .jdbcAuthentication()
//        .dataSource(dataSource);
//        .usersByUsernameQuery(
//            "select username, password, true " +
//                "from trainer where username=?")
//        .authoritiesByUsernameQuery(
//            "select username, 'ROLE_USER' from Spitter where username=?");

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
//          .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**").permitAll()
          .antMatchers(HttpMethod.GET, "/").permitAll()
          .antMatchers("/login").permitAll()
//        .antMatchers("/index").authenticated()
          .antMatchers("/register").permitAll()
          .antMatchers("/pokos").hasRole("USER")
          .anyRequest().authenticated()
        .and()
          .formLogin().loginPage("/login").permitAll()
        .and()
          .logout()
          .logoutSuccessUrl("/login?logout")
          .permitAll();
  }
}
