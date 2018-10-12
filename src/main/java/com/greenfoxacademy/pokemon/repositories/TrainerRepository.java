package com.greenfoxacademy.pokemon.repositories;

import com.greenfoxacademy.pokemon.models.Trainer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainerRepository extends CrudRepository<Trainer, Long> {
  List<Trainer> findAll();
  Trainer findByTrainername(String trainername);
  Trainer findFirstByTrainername(String trainername);
  Trainer findByTrainerid(Long id);
//  Trainer findTopByTrainernameOrderByTraineridDesc(String trainername);
}
