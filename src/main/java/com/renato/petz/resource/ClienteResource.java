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
import com.renato.petz.model.Cliente;
import com.renato.petz.model.to.ClienteTO;
import com.renato.petz.service.ClienteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Cliente")
@RestController
@RequestMapping("/petz/v1/cliente")
public class ClienteResource {
	
	@Autowired
	private ClienteService clienteService;
	
	@ApiOperation(value = "Busca todos os clientes")
	@GetMapping
	public ResponseEntity<Page<Cliente>> buscaTodosClientes(Pageable pageable) {
		
		Page<Cliente> clientes = clienteService.buscaTodosClientes(pageable);
		
		if (null == clientes || !clientes.hasContent()) {
			throw new NegocioException("Nenhum dado encontrado!", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Page<Cliente>>(clientes, HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "Busca Cliente por id")
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscaCliente(@PathVariable("id") Long idCliente) {
		
		Cliente cliente = clienteService.buscaCliente(idCliente);
		
		if (null == cliente) {
			throw new NegocioException("Cliente não encontrado!", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "Atualiza cliente")
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizaCliente(@PathVariable("id") Long idCliente, @RequestBody ClienteTO clienteTO) {
		
		Cliente clienteAtualizado = clienteService.atualizaCliente(idCliente, clienteTO);
		
		if (null == clienteAtualizado) {
			throw new NegocioException("Cliente não encontrado!", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Cliente>(clienteAtualizado, HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "Cadastra cliente")
	@PostMapping
	public ResponseEntity<Cliente> cadastraCliente(@RequestBody ClienteTO clienteTO) {
		
		Cliente clienteCadastrado = clienteService.cadastraCliente(clienteTO);
		
		if (null == clienteCadastrado) {
			throw new NegocioException("Cliente não encontrado!", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Cliente>(clienteCadastrado, HttpStatus.CREATED);
		
	}
	
	@ApiOperation(value = "Deleta cliente")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletaCliente(@PathVariable("id") Long idCliente) {
		
		try {
			
			clienteService.deletaCliente(idCliente);
			
		} catch (Exception e) {

			throw new NegocioException(e.getMessage());
			
		}
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}

}
