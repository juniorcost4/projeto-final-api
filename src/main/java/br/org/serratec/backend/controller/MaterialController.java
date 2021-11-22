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

import br.org.serratec.backend.dto.MaterialAtualizarDTO;
import br.org.serratec.backend.dto.MaterialDTO;
import br.org.serratec.backend.dto.MaterialInserirDTO;
import br.org.serratec.backend.exception.DescricaoNullaException;
import br.org.serratec.backend.exception.NotFoundException;
import br.org.serratec.backend.service.MaterialService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/materiais")
public class MaterialController {
	@Autowired
	private MaterialService materialService;
	
	@PostMapping
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Material cadastrado com sucesso"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
    })
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> inserir(@RequestBody MaterialInserirDTO materialInserirDTO) {
		try {
			MaterialDTO materialDTO = materialService.inserir(materialInserirDTO);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(materialDTO.getId())
					.toUri();
			return ResponseEntity.created(uri).body(materialDTO);
		} catch (DescricaoNullaException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage()); 
		}
	}
	
    @GetMapping
    @ApiOperation(value = "Listar todos os materiais", notes = "Listagem de Materiais")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna todos os materiais"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
    })
    public List<MaterialDTO> listar() {
        return materialService.listar();
    }
    
	@GetMapping("/{id}")
	@ApiOperation(value = "Buscar um material pelo id", notes = "Buscar material")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna o material"),
			@ApiResponse(code = 401, message = "Erro de autenticacao"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso nao encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor"),
			
	})
	public ResponseEntity<Object> listarPorId(@PathVariable Long id) {
		
		if (materialService.listarPorId(id) != null) {
			return ResponseEntity.ok(materialService.listarPorId(id));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Atualiza um material pelo id", notes = "Atualizar material")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna o material atualizado"),
			@ApiResponse(code = 401, message = "Erro de autenticacao"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso nao encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor"),
			
	})
	public ResponseEntity<Object> atualizar(@PathVariable Long id, @RequestBody MaterialAtualizarDTO materialAtualizarDTO) {
		try {
			return ResponseEntity.ok(materialService.atualizar(id, materialAtualizarDTO));
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deleta um material pelo id", notes = "Deletar material")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "Material deletado com sucesso"),
			@ApiResponse(code = 401, message = "Erro de autenticacao"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso nao encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor"),
			
	})
	public ResponseEntity<Object> deletar(@PathVariable Long id) {
		try {
			materialService.deletar(id);
			return ResponseEntity.noContent().build();
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
}
