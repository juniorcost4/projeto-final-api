package br.org.serratec.backend.dto;

import br.org.serratec.backend.model.Item;
import br.org.serratec.backend.model.Material;

public class MaterialDTO {
	private Long id;
	private String descricao;
	private Integer quantidadeItens;
	private Double precoMaterial;
	
	public MaterialDTO() {

	}
	
	public MaterialDTO(Material material, Item item) {
		this.id = material.getId();
		this.descricao = material.getDescricao();
		this.quantidadeItens = item.getQuantidadeItens();
		this.precoMaterial = item.getPrecoMaterial();
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
