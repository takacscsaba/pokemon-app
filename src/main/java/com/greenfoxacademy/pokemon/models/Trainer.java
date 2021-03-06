package com.greenfoxacademy.pokemon.models;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
//@Table(name = "trainer")
public class Trainer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long trainerid;

  @Length(max = 20)
  @Column(unique = true, nullable = false)
  private String trainername;

  @Column(nullable = false)
  private String trainerpassword;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<Pokemon> pokemons;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//  @JoinTable(
//      name = "trainer_role",
//      joinColumns = {@JoinColumn(name = "trainer_id")},
//      inverseJoinColumns = {@JoinColumn(name = "role_id")}
//  )
  private Set<Role> roles = new HashSet<>();

  private String activation;

  private Boolean enabled;

  public Trainer(@Length(max = 20) String trainername, String trainerpassword) {
    this.trainername = trainername;
    this.trainerpassword = trainerpassword;
  }

  public Trainer() {
  }

  public Long getTrainerid() {
    return trainerid;
  }

  public void setTrainerid(Long trainerid) {
    this.trainerid = trainerid;
  }

  public String getTrainername() {
    return trainername;
  }

  public void setTrainername(String trainername) {
    this.trainername = trainername;
  }

  public String getTrainerpassword() {
    return trainerpassword;
  }

  public void setTrainerpassword(String trainerpassword) {
    this.trainerpassword = trainerpassword;
  }

  public List<Pokemon> getPokemons() {
    return pokemons;
  }

  public void setPokemons(List<Pokemon> pokemons) {
    this.pokemons = pokemons;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void addRoles(String roleName) {
    if (this.roles == null || this.roles.isEmpty())
      this.roles = new HashSet<>();
    this.roles.add(new Role(roleName));
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  public Boolean getEnabled() {
    return enabled;
  }

  public String getActivation() {
    return activation;
  }

  public void setActivation(String activation) {
    this.activation = activation;
  }
}
