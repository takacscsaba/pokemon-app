package com.greenfoxacademy.pokemon.services;

import com.greenfoxacademy.pokemon.models.Pokemon;
import com.greenfoxacademy.pokemon.models.Trainer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PokemonService {
  boolean isLoggedInTrainerIdNull();

  Trainer getLoggedInTrainer();

  List<Pokemon> getAllPokemon();

  List<Pokemon> getAllPokemonOfLoggedInTrainer();

  Trainer trainerCreator();

  String loginAuth(Trainer trainer);

  void idSaver(Trainer trainer);

  void addPokemon(String pokemonName);

  void removePokemon(Long pokemonId);

  boolean isTrainerNotExistsByName(Trainer trainer);

  String trainerSaver(Trainer trainer);

  void loggedInTrainerIdNuller();
}
