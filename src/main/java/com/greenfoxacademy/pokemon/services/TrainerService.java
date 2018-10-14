package com.greenfoxacademy.pokemon.services;

import com.greenfoxacademy.pokemon.models.Trainer;

public interface TrainerService {

  Trainer findByTrainerName(String trainerName);

}
