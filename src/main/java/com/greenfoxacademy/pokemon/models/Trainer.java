package com.greenfoxacademy.pokemon.models;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.List;

@Entity
public class Trainer {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long trainerid;
  @Length(max = 20)
  private String trainername;
  @Length(max = 20)
  private String trainerpassword;
  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<Pokemon> pokemons;

  public Trainer(@Length(max = 20) String trainername, @Length(max = 20) String trainerpassword) {
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
}
