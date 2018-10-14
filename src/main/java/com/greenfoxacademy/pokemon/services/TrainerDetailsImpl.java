package com.greenfoxacademy.pokemon.services;

import com.greenfoxacademy.pokemon.models.Trainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import com.greenfoxacademy.pokemon.models.Role;

public class TrainerDetailsImpl implements UserDetails {

  private Trainer trainer;

  public TrainerDetailsImpl(Trainer trainer) {
    this.trainer = trainer;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
    Set<Role> roles = trainer.getRoles();
    for (Role role : roles) {
      authorities.add(new SimpleGrantedAuthority(role.getRole()));
    }
    return authorities;
  }

  @Override
  public String getPassword() {
    return trainer.getTrainerpassword();
  }

  @Override
  public String getUsername() {
    return trainer.getTrainername();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
