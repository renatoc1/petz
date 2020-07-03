package com.renato.petz.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.renato.petz.exception.NegocioException;
import com.renato.petz.model.Pet;
import com.renato.petz.model.to.ClienteTO;
import com.renato.petz.model.to.PetTO;
import com.renato.petz.repository.PetRepository;

@Service
public class PetService {

	@Autowired
	private PetRepository petRepository;
	
	public Page<Pet> buscaTodosPets(Pageable pageable) {
		return petRepository.findAll(pageable);
	}

	public Pet buscaPet(Long idPet) {
		return petRepository.findById(idPet).orElse(null);
	}

	public Pet buscaPorMatricula(String matricula) {
		return petRepository.findByMatricula(matricula);
	}

	@Transactional
	public Pet atualizaPet(Long idPet, PetTO petTO) {
		Pet pet = buscaPet(idPet);
		if (null == pet) {
			throw new NegocioException("Pet não encontrado!", HttpStatus.NOT_FOUND);
		}
		Pet petAtualizado = petTO.parse(pet);
		petRepository.save(petAtualizado);
		return petAtualizado;
	}

	@Transactional
	public Pet cadastraPet(PetTO petTO) {
		Pet pet = buscaPorMatricula(petTO.getMatricula());
		if (null != pet) {
			throw new NegocioException("Pet já cadastrado!!");
		}
		Pet petCadastrado = petTO.builder();
		petRepository.save(petCadastrado);
		return petCadastrado;
	}

	public void deletaPet(Long idPet) {
		
		Pet pet = buscaPet(idPet);

		if (null == pet) {
			throw new NegocioException("Pet não encontrado!", HttpStatus.NOT_FOUND);
		}
		
		petRepository.delete(pet);

	}

	
}
