package br.org.serratec.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.backend.dto.ClienteAtualizarDTO;
import br.org.serratec.backend.dto.ClienteDTO;
import br.org.serratec.backend.dto.ClienteInserirDTO;
import br.org.serratec.backend.exception.NomeException;
import br.org.serratec.backend.exception.NotFoundException;
import br.org.serratec.backend.model.Cliente;
import br.org.serratec.backend.repository.ClienteRepository;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    
    public List<ClienteDTO> listar() {
        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteDTO> clientesDTO = new ArrayList<ClienteDTO>();

        for (Cliente cliente : clientes) {
          ClienteDTO clienteDTO = new ClienteDTO(cliente);
            clientesDTO.add(clienteDTO);
        }
        return clientesDTO;
    }
    
    public ClienteDTO listarPorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        ClienteDTO clienteDTO = new ClienteDTO();
        
        if (cliente.isPresent()) {
        	clienteDTO.setId(cliente.get().getId());
        	clienteDTO.setNome(cliente.get().getNome());
        	
        	return clienteDTO;
        }
        
        return null;
    }
    
    public ClienteDTO inserir(ClienteInserirDTO clienteInserirDTO) throws NomeException {
		
    	if(clienteRepository.findByNome(clienteInserirDTO.getNome()) != null) {
    		throw new NomeException("Nome de cliente ja cadastrado!");
    	}
    	
    	Cliente cliente = new Cliente();
    	
    	cliente.setNome(clienteInserirDTO.getNome());
    	
    	clienteRepository.save(cliente);
    	
    	return new ClienteDTO(cliente);
    }
    
    public ClienteDTO atualizar(Long id, ClienteAtualizarDTO clienteAtualizarDTO) throws NotFoundException {
		
    	if(!clienteRepository.existsById(id)) {
    		throw new NotFoundException();
    	}
    	
    	Cliente cliente = new Cliente();
    	
    	cliente.setId(id);
    	cliente.setNome(clienteAtualizarDTO.getNome());
    	
    	clienteRepository.save(cliente);
    	
    	return new ClienteDTO(cliente);
    }
    
    public void deletar(Long id) throws NotFoundException {
    	if(!clienteRepository.existsById(id)) {
    		throw new NotFoundException();
    	}
    	
    	clienteRepository.deleteById(id);
    }
}
