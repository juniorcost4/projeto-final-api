package br.org.serratec.backend.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.serratec.backend.dto.ClienteAtualizarDTO;
import br.org.serratec.backend.dto.ClienteDTO;
import br.org.serratec.backend.dto.ClienteInserirDTO;
import br.org.serratec.backend.exception.NomeException;
import br.org.serratec.backend.exception.NotFoundException;
import br.org.serratec.backend.service.ClienteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	@Autowired
	private ClienteService clienteService;
	
	@PostMapping
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Cliente cadastrado com sucesso"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
    })
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> inserir(@RequestBody ClienteInserirDTO clienteInserirDTO) {
		try {
			ClienteDTO clienteDTO = clienteService.inserir(clienteInserirDTO);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(clienteDTO.getId())
					.toUri();
			return ResponseEntity.created(uri).body(clienteDTO);
		} catch (NomeException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage()); 
		}
	}
	
    @GetMapping
    @ApiOperation(value = "Listar todos os clientes", notes = "Listagem de Clientes")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna todos os clientes"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
    })
    public List<ClienteDTO> listar() {
        return clienteService.listar();
    }
    
	@GetMapping("/{id}")
	@ApiOperation(value = "Buscar um cliente pelo id", notes = "Buscar cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna o cliente"),
			@ApiResponse(code = 401, message = "Erro de autenticacao"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso nao encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor"),
			
	})
	public ResponseEntity<Object> listarPorId(@PathVariable Long id) {
		
		if (clienteService.listarPorId(id) != null) {
			return ResponseEntity.ok(clienteService.listarPorId(id));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Atualiza um cliente pelo id", notes = "Atualizar cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna o cliente atualizado"),
			@ApiResponse(code = 401, message = "Erro de autenticacao"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso nao encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor"),
			
	})
	public ResponseEntity<Object> atualizar(@PathVariable Long id, @RequestBody ClienteAtualizarDTO clienteAtualizarDTO) {
		try {
			return ResponseEntity.ok(clienteService.atualizar(id, clienteAtualizarDTO));
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deleta um cliente pelo id", notes = "Deletar cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "Cliente deletado com sucesso"),
			@ApiResponse(code = 401, message = "Erro de autenticacao"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso nao encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor"),
			
	})
	public ResponseEntity<Object> deletar(@PathVariable Long id) {
		try {
			clienteService.deletar(id);
			return ResponseEntity.noContent().build();
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
}
