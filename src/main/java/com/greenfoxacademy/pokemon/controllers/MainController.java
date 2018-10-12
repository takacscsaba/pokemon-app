package com.greenfoxacademy.pokemon.controllers;

import com.greenfoxacademy.pokemon.models.Pokemon;
import com.greenfoxacademy.pokemon.models.Trainer;
import com.greenfoxacademy.pokemon.repositories.PokemonRepository;
import com.greenfoxacademy.pokemon.repositories.TrainerRepository;
import com.greenfoxacademy.pokemon.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
  private PokemonService pokemonService;

  @Autowired
  public MainController(PokemonService pokemonService) {
    this.pokemonService = pokemonService;
  }

  @GetMapping("/")
  public String redirecter() {
    if (pokemonService.isLoggedInTrainerIdNull()) {
      return "redirect:/loginuser";
    } else {
      return "redirect:/index";
    }
  }

  @GetMapping("/index")
  public String indexPage(Model model) {
    if (!pokemonService.isLoggedInTrainerIdNull()) {
      model.addAttribute("trainer", pokemonService.getLoggedInTrainer());
      model.addAttribute("pokemons", pokemonService.getAllPokemon());
      model.addAttribute("pokemonsOfTrainer", pokemonService.getAllPokemonOfLoggedInTrainer());
      return "index";
    }
    return "redirect:/loginuser";
  }

  @GetMapping("/loginuser")
  public String loginPage(Model model) {
    model.addAttribute("trainer", pokemonService.trainerCreator());
    return "login";
  }

  @PostMapping("/loginuser")
  public String loggingIn(@ModelAttribute Trainer trainer) {
    return pokemonService.loginAuth(trainer);
  }

  @PostMapping("/addpokemon")
  public String addPokemon(@RequestParam(value = "pokemonName") String pokemonName) {
    pokemonService.addPokemon(pokemonName);
    return "redirect:/index";
  }

  @PostMapping("/removepokemon")
  public String removePokemon(@RequestParam(value = "pokemonId") Long pokemonId) {
    pokemonService.removePokemon(pokemonId);
    return "redirect:/index";
  }

  @GetMapping("/register")
  public String registerPage(Model model) {
    model.addAttribute("trainer", pokemonService.trainerCreator());
    return "register";
  }

  @PostMapping("/register")
  public String registerTrainer(@ModelAttribute Trainer trainer) {
    if (pokemonService.isTrainerAlreadyExistsByName(trainer)) {
      pokemonService.trainerSaver(trainer);
      return "redirect:/loginuser";
    }
    return "redirect:/register";
  }

  @PostMapping("/signout")
  public String signOut() {
    pokemonService.loggedInTrainerIdNuller();
    return "redirect:/";
  }
}
