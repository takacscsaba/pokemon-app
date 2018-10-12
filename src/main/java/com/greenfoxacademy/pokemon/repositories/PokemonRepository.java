package com.greenfoxacademy.pokemon.repositories;

import com.greenfoxacademy.pokemon.models.Pokemon;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends CrudRepository<Pokemon, Long> {
  Pokemon findByTname(String name);
  Pokemon findByPid(Long id);
//  List<Pokemon> findByTrainerTrainerid(Long id);
}
