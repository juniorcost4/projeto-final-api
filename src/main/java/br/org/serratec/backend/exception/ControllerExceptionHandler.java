package br.org.serratec.backend.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(NomeException.class)
	public ResponseEntity<Object> handleEmailException(NomeException ex) {
		NomeException nomeException = new NomeException(ex.getMessage());
		return ResponseEntity.unprocessableEntity().body(nomeException);
	}
	
	@ExceptionHandler(DescricaoNullaException.class)
	public ResponseEntity<Object> handleDescricaoNullaException(DescricaoNullaException ex) {
		DescricaoNullaException descricaoNullaException = new DescricaoNullaException(ex.getMessage());
		return ResponseEntity.unprocessableEntity().body(descricaoNullaException);
	}
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
		return ResponseEntity.notFound().build();
	}
}
