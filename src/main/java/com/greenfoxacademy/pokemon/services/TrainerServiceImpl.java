package com.greenfoxacademy.pokemon.services;

import com.greenfoxacademy.pokemon.models.Trainer;
import com.greenfoxacademy.pokemon.repositories.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TrainerServiceImpl implements TrainerService, UserDetailsService {

  private TrainerRepository trainerRepository;

  @Autowired
  public TrainerServiceImpl(TrainerRepository trainerRepository) {
    this.trainerRepository = trainerRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String trainerName) throws UsernameNotFoundException {
    System.out.println("Trainer keresés");
    Trainer trainer = findByTrainerName(trainerName);
    if (trainer == null) {
      throw new UsernameNotFoundException(trainerName);
    }
    return new TrainerDetailsImpl(trainer);
  }

  @Override
  public Trainer findByTrainerName(String trainerName) {
    return trainerRepository.findByTrainername(trainerName);
  }
}
