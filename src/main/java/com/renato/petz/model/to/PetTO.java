package com.renato.petz.model.to;

import com.renato.petz.model.Pet;

import lombok.Data;

@Data
public class PetTO {

	private String nome;
	
	private String matricula;
	
	private String raca;
	
	public Pet parse(Pet pet) {
		pet.setNome(this.nome);
		pet.setMatricula(this.matricula);
		pet.setRaca(this.raca);
		return pet;
	}

	public Pet builder() {
		return Pet.builder()
				.nome(this.nome)
				.matricula(this.matricula)
				.raca(this.raca)
				.build();
	}
	
}
