package br.org.serratec.backend.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Item {
	@Column(name= "quantidade_itens")
	private Integer quantidadeItens;
	@Column(name = "preco_material")
	private Double precoMaterial;
	
	public Integer getQuantidadeItens() {
		return quantidadeItens;
	}
	public void setQuantidadeItens(Integer quantidadeItens) {
		this.quantidadeItens = quantidadeItens;
	}
	public Double getPrecoMaterial() {
		return precoMaterial;
	}
	public void setPrecoMaterial(Double precoMaterial) {
		this.precoMaterial = precoMaterial;
	}
}
