package com.greenfoxacademy.pokemon.services;

import com.greenfoxacademy.pokemon.models.Pokemon;
import com.greenfoxacademy.pokemon.models.Trainer;
import com.greenfoxacademy.pokemon.repositories.PokemonRepository;
import com.greenfoxacademy.pokemon.repositories.TrainerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonServiceImpl implements PokemonService {
  private PokemonRepository pokemonRepository;
  private TrainerRepository trainerRepository;
  private Long loggedInTrainerId;

  public PokemonServiceImpl(PokemonRepository pokemonRepository, TrainerRepository trainerRepository) {
    this.pokemonRepository = pokemonRepository;
    this.trainerRepository = trainerRepository;
  }

  @Override
  public boolean isLoggedInTrainerIdNull() {
    if (loggedInTrainerId == null)
      return true;
    else
      return false;
  }

  @Override
  public Trainer getLoggedInTrainer() {
    return trainerRepository.findByTrainerid(loggedInTrainerId);
  }

  @Override
  public List<Pokemon> getAllPokemon() {
    return pokemonRepository.findAll();
  }

  @Override
  public List<Pokemon> getAllPokemonOfLoggedInTrainer() {
    return getLoggedInTrainer().getPokemons();
  }

  @Override
  public Trainer trainerCreator() {
    return new Trainer();
  }

  @Override
  public String loginAuth(Trainer trainer) {
    if (trainerRepository.findByTrainername(trainer.getTrainername()) != null) {
      trainerRepository.save(trainer);
      if (trainer.getTrainerpassword().equals(trainerRepository.findFirstByTrainername(trainer.getTrainername()).getTrainerpassword())) {
        idSaver(trainerRepository.findFirstByTrainername(trainer.getTrainername()));
        trainerRepository.delete(trainer);
        return "redirect:/index";
      }
      trainerRepository.delete(trainer);
    }
    return "redirect:/";
  }

  @Override
  public void idSaver(Trainer trainer) {
    this.loggedInTrainerId = trainer.getTrainerid();
  }

  @Override
  public void addPokemon(String pokemonName) {
    Pokemon pokemon = pokemonRepository.findByTname(pokemonName);
    Trainer trainer = trainerRepository.findByTrainerid(loggedInTrainerId);
    trainer.getPokemons().add(pokemon);
//    pokemon.setTrainer(trainer);
//    pokemonRepository.save(pokemon);
    trainerRepository.save(trainer);
  }

  @Override
  public void removePokemon(Long pokemonId) {
    Pokemon pokemon = pokemonRepository.findByPid(pokemonId);
    Trainer trainer = trainerRepository.findByTrainerid(loggedInTrainerId);
    trainer.getPokemons().remove(pokemon);
//    pokemon.setTrainerNull();
    pokemonRepository.save(pokemon);
    trainerRepository.save(trainer);
  }

  @Override
  public boolean isTrainerAlreadyExistsByName(Trainer trainer) {
    if (trainerRepository.findByTrainername(trainer.getTrainername()) == null)
      return true;
    else
      return false;
  }

  @Override
  public void trainerSaver(Trainer trainer) {
    trainerRepository.save(trainer);
  }

  @Override
  public void loggedInTrainerIdNuller() {
    this.loggedInTrainerId = null;
  }
}
