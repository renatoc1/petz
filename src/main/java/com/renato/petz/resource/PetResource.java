package com.renato.petz.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renato.petz.exception.NegocioException;
import com.renato.petz.model.Pet;
import com.renato.petz.model.to.PetTO;
import com.renato.petz.service.PetService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Pet")
@RestController
@RequestMapping("/petz/v1/pet")
public class PetResource {

	@Autowired
	private PetService petService;
	
	@ApiOperation(value = "Busca todos os pets")
	@GetMapping
	public ResponseEntity<Page<Pet>> buscaTodosPets(Pageable pageable) {
		
		Page<Pet> pets = petService.buscaTodosPets(pageable);
		
		if (null == pets || !pets.hasContent()) {
			throw new NegocioException("Nenhum dado encontrado!", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Page<Pet>>(pets, HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "Busca pet por id")
	@GetMapping("/{id}")
	public ResponseEntity<Pet> buscaPet(@PathVariable("id") Long idPet) {
		
		Pet pet = petService.buscaPet(idPet);
		
		if (null == pet) {
			throw new NegocioException("Pet não encontrado!", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Pet>(pet, HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "Atualiza pet")
	@PutMapping("/{id}")
	public ResponseEntity<Pet> atualizaPet(@PathVariable("id") Long idPet, @RequestBody PetTO petTO) {
		
		Pet petAtualizado = petService.atualizaPet(idPet, petTO);
		
		if (null == petAtualizado) {
			throw new NegocioException("Pet não encontrado!", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Pet>(petAtualizado, HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "Cadastra pet")
	@PostMapping
	public ResponseEntity<Pet> cadastraPet(@RequestBody PetTO petTO) {
		
		Pet petCadastrado = petService.cadastraPet(petTO);
		
		if (null == petCadastrado) {
			throw new NegocioException("Pet não encontrado!", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Pet>(petCadastrado, HttpStatus.CREATED);
		
	}
	
	@ApiOperation(value = "Deleta Pet")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletaPet(@PathVariable("id") Long idPet) {
		
		try {
			
			petService.deletaPet(idPet);
			
		} catch (Exception e) {

			throw new NegocioException(e.getMessage());
			
		}
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
}
