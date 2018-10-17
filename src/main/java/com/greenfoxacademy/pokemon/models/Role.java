package com.greenfoxacademy.pokemon.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  private String role;

  @ManyToMany(mappedBy = "roles")
  private Set<Trainer> trainers = new HashSet<Trainer>();

  public Role(String role) {
    this.role = role;
  }

  public Role() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public Set<Trainer> getTrainers() {
    return trainers;
  }

  public void setTrainers(Set<Trainer> trainers) {
    this.trainers = trainers;
  }

  @Override
  public String toString() {
    return "Role{" +
        "id=" + id +
        ", role='" + role + '\'' +
        '}';
  }
}
