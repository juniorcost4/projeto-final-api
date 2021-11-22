package br.org.serratec.backend.dto;

import br.org.serratec.backend.model.Item;
import br.org.serratec.backend.model.Material;

public class MaterialAtualizarDTO {
	private String descricao;
	private Integer quantidadeItens;
	private Double precoMaterial;
	
	public MaterialAtualizarDTO() {

	}
	
	public MaterialAtualizarDTO(Material material, Item item) {
		this.descricao = material.getDescricao();
		this.quantidadeItens = item.getQuantidadeItens();
		this.precoMaterial = item.getPrecoMaterial();
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
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
