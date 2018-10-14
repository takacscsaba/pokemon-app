package com.greenfoxacademy.pokemon;

import com.greenfoxacademy.pokemon.models.Pokemon;
import com.greenfoxacademy.pokemon.models.Trainer;
import com.greenfoxacademy.pokemon.repositories.PokemonRepository;
import com.greenfoxacademy.pokemon.repositories.TrainerRepository;
import org.apache.catalina.LifecycleState;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class PokemonApplication implements CommandLineRunner {

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  public static void main(String[] args) {
    SpringApplication.run(PokemonApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
//    trainerRepository.findByTrainername("Csabi").getPokemons().add(pokemonRepository.findByTname("Charizard"));
//    trainerRepository.save(trainerRepository.findByTrainername("Csabi"));
//    trainerRepository.save(new Trainer("Csabi", "charizard"));
//    trainerRepository.save(new Trainer("Friendless Baxter", "psyduck"));
  }
}
