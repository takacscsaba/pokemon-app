package com.greenfoxacademy.pokemon.controllers;

import com.greenfoxacademy.pokemon.models.Pokemon;
import com.greenfoxacademy.pokemon.repositories.PokemonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainRestController {
  private PokemonRepository pokemonRepository;

  public MainRestController(PokemonRepository pokemonRepository) {
    this.pokemonRepository = pokemonRepository;
  }

  @GetMapping("/pokos")
  public ResponseEntity<?> listOnePoko() {
    return ResponseEntity.status(HttpStatus.FOUND).body(pokemonRepository.findByPid(4L));
  }
}
