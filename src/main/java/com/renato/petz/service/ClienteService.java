package com.renato.petz.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.renato.petz.exception.NegocioException;
import com.renato.petz.model.Cliente;
import com.renato.petz.model.to.ClienteTO;
import com.renato.petz.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Page<Cliente> buscaTodosClientes(Pageable pageable) {
		return clienteRepository.findAll(pageable);
	}

	public Cliente buscaCliente(Long idCliente) {
		return clienteRepository.findById(idCliente).orElse(null);
	}

	public Cliente buscaPorCpf(String cpf) {
		return clienteRepository.findByCpf(cpf);
	}

	@Transactional
	public Cliente atualizaCliente(Long idCliente, ClienteTO clienteTO) {
		Cliente cliente = buscaCliente(idCliente);
		if (null == cliente) {
			throw new NegocioException("Cliente não encontrado!", HttpStatus.NOT_FOUND);
		}
		Cliente clienteAtualizado = clienteTO.parse(cliente);
		clienteRepository.save(clienteAtualizado);
		return clienteAtualizado;
	}

	@Transactional
	public Cliente cadastraCliente(ClienteTO clienteTO) {
		Cliente cliente = buscaPorCpf(clienteTO.getCpf());
		if (null != cliente) {
			throw new NegocioException("Cliente já cadastrado!!");
		}
		Cliente clienteCadastrado = clienteTO.builder();
		clienteRepository.save(clienteCadastrado);
		return clienteCadastrado;
	}

	public void deletaCliente(Long idCliente) {
		
		Cliente cliente = buscaCliente(idCliente);

		if (null == cliente) {
			throw new NegocioException("Cliente não encontrado!", HttpStatus.NOT_FOUND);
		}
		
		clienteRepository.delete(cliente);

	}

}
