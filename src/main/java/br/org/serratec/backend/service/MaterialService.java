package br.org.serratec.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.backend.dto.MaterialAtualizarDTO;
import br.org.serratec.backend.dto.MaterialDTO;
import br.org.serratec.backend.dto.MaterialInserirDTO;
import br.org.serratec.backend.exception.DescricaoNullaException;
import br.org.serratec.backend.exception.NotFoundException;
import br.org.serratec.backend.model.Item;
import br.org.serratec.backend.model.Material;
import br.org.serratec.backend.repository.MaterialRepository;

@Service
public class MaterialService {
	@Autowired
	private MaterialRepository materialRepository;
	
    public List<MaterialDTO> listar() {
        List<Material> materiais = materialRepository.findAll();
        List<MaterialDTO> materiaisDTO = new ArrayList<MaterialDTO>();

        for (Material material : materiais) {
        	MaterialDTO materialDTO = new MaterialDTO(material, material.getItem());
        	materiaisDTO.add(materialDTO);
        }
        return materiaisDTO;
    }
    
    public MaterialDTO listarPorId(Long id) {
        Optional<Material> material = materialRepository.findById(id);
        MaterialDTO materialDTO = new MaterialDTO();
        
        if (material.isPresent()) {
        	materialDTO.setId(material.get().getId());
        	materialDTO.setDescricao(material.get().getDescricao());
        	materialDTO.setPrecoMaterial(material.get().getItem().getPrecoMaterial());        	
        	materialDTO.setQuantidadeItens(material.get().getItem().getQuantidadeItens());
        	return materialDTO;
        }
        
        return null;
    }
    
    public MaterialDTO inserir(MaterialInserirDTO materialInserirDTO) throws DescricaoNullaException {
		
    	if(materialInserirDTO.getDescricao().equals(null)) {
    		throw new DescricaoNullaException("Descricao do material nao pode ser nula!");
    	}
    	
    	Material material = new Material();
    	Item item = new Item(materialInserirDTO.getQuantidadeItens(), materialInserirDTO.getPrecoMaterial());
    	
    	material.setDescricao(materialInserirDTO.getDescricao());    	
    	material.setItem(item);
    	
    	materialRepository.save(material);
    	
    	return new MaterialDTO(material, item);
    }
    
    public MaterialDTO atualizar(Long id, MaterialAtualizarDTO materialAtualizarDTO) throws NotFoundException {
		
    	if(!materialRepository.existsById(id)) {
    		throw new NotFoundException();
    	}
    	
    	Material material = new Material();
    	Item item = new Item();
    	item.setPrecoMaterial(materialAtualizarDTO.getPrecoMaterial());
    	item.setQuantidadeItens(materialAtualizarDTO.getQuantidadeItens());
    	
    	material.setId(id);
    	material.setDescricao(materialAtualizarDTO.getDescricao());
    	material.setItem(item);
    	
    	materialRepository.save(material);
    	
    	return new MaterialDTO(material, item);
    }
    
    public void deletar(Long id) throws NotFoundException {
    	if(!materialRepository.existsById(id)) {
    		throw new NotFoundException();
    	}
    	
    	materialRepository.deleteById(id);
    }
}
