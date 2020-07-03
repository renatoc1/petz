package com.renato.petz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.renato.petz.model.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long>{

	public Pet findByMatricula(String matricula);
	
}
