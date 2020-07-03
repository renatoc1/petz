package com.renato.petz.model.to;

import com.renato.petz.model.Cliente;

import lombok.Data;

@Data
public class ClienteTO {

	private String nome;
	
	private String cpf;

	private Long idade;

	private String sexo;

	private String profissao;

	private String telefone;

	public Cliente parse(Cliente cliente) {
		cliente.setNome(this.nome);
		cliente.setCpf(this.cpf);
		cliente.setIdade(this.idade);
		cliente.setSexo(this.sexo);
		cliente.setProfissao(this.profissao);
		cliente.setTelefone(this.telefone);
		return cliente;
	}

	public Cliente builder() {
		return Cliente.builder()
				.nome(this.nome)
				.cpf(this.cpf)
				.idade(this.idade)
				.sexo(this.sexo)
				.profissao(this.profissao)
				.telefone(this.telefone)
				.build();
	}

}
