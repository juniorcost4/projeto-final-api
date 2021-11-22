package br.org.serratec.backend.dto;

public class ClienteInserirDTO {
	private String nome;
	
	public ClienteInserirDTO() {

	}

	public ClienteInserirDTO(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
