package com.greenfoxacademy.pokemon.repositories;

import com.greenfoxacademy.pokemon.models.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
  Role findByRole(String role);
}
