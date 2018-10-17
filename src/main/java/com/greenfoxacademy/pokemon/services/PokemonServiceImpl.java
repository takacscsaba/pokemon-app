package com.greenfoxacademy.pokemon.services;

import com.greenfoxacademy.pokemon.models.Pokemon;
import com.greenfoxacademy.pokemon.models.Role;
import com.greenfoxacademy.pokemon.models.Trainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.greenfoxacademy.pokemon.repositories.PokemonRepository;
import com.greenfoxacademy.pokemon.repositories.RoleRepository;
import com.greenfoxacademy.pokemon.repositories.TrainerRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class PokemonServiceImpl implements PokemonService {
  private final Logger log = LoggerFactory.getLogger(this.getClass());
  private PokemonRepository pokemonRepository;
  private TrainerRepository trainerRepository;
  private RoleRepository roleRepository;
  private Long loggedInTrainerId;

  private final String USER_ROLE = "USER";

  public PokemonServiceImpl(PokemonRepository pokemonRepository, TrainerRepository trainerRepository, RoleRepository roleRepository) {
    this.pokemonRepository = pokemonRepository;
    this.trainerRepository = trainerRepository;
    this.roleRepository = roleRepository;
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
//    return trainerRepository.findByTrainerid(loggedInTrainerId);
    return trainerRepository.findByTrainername(logInTrainer());
  }

  public String logInTrainer() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentPrincipalName = authentication.getName();
    this.loggedInTrainerId = trainerRepository.findByTrainername(currentPrincipalName).getTrainerid();
    return currentPrincipalName;
  }

  @Override
  public List<Pokemon> getAllPokemon() {
    return pokemonRepository.findAll();
  }

  @Override
  public List<Pokemon> getAllPokemonOfLoggedInTrainer() {
    try {
      return getLoggedInTrainer().getPokemons();
    } catch (Exception e) {
      log.info("NO POKEMONS");
    }
    return null;
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
  public boolean isTrainerNotExistsByName(Trainer trainer) {
    if (trainerRepository.findByTrainername(trainer.getTrainername()) == null)
      return true;
    else
      return false;
  }

  @Override
  public String trainerSaver(Trainer trainerToRegister) {
    Trainer trainerCheck = trainerRepository.findByTrainername(trainerToRegister.getTrainername());

    if (trainerCheck != null)
      return "alreadyExists";

    Role trainerRole = roleRepository.findByRole(USER_ROLE);

    if (trainerRole != null) {
      trainerToRegister.getRoles().add(trainerRole);
    } else {
      trainerToRegister.addRoles(USER_ROLE);
    }

    trainerToRegister.setEnabled(false);
    trainerToRegister.setActivation(generateKey());
    trainerRepository.save(trainerToRegister);

    return "ok";
  }

  @Override
  public void loggedInTrainerIdNuller() {
    this.loggedInTrainerId = null;
  }

  public String generateKey() {
    String key = "";
    Random random = new Random();
    char[] word = new char[16];
    for (int j = 0; j < word.length; j++) {
      word[j] = (char) ('a' + random.nextInt(26));
    }
    String toReturn = new String(word);
    log.debug("random code: " + toReturn);
    return new String(word);
  }
}
