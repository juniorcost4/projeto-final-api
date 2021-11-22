package br.org.serratec.backend.dto;

public class ClienteAtualizarDTO {
	private String nome;
	
	public ClienteAtualizarDTO() {

	}

	public ClienteAtualizarDTO(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
