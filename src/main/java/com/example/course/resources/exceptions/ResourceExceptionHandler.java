package com.example.course.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.course.services.exceptions.DatabaseException;
import com.example.course.services.exceptions.ResourceNotFoundException;

//Nesso classe vamos dar o tratamento manual para os erros; 
@ControllerAdvice //intercepta as exceções que acontecerem para que este objeto possa executar um possivel tartamento
public class ResourceExceptionHandler{
	
	//primeiro tratamento: da ResourceNotFoundException
	@ExceptionHandler(ResourceNotFoundException.class)//annotation para que ele consiga interceptar a requisição que deu exceção para cair aqui; vai interceptar toda exceção do tipo declarado aqui 
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){//metodo pode ter o nome que eu quiser
		String error = "Recurso não encontrado";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DatabaseException.class) 
	public ResponseEntity<StandardError> dataBase(DatabaseException e, HttpServletRequest request){
		String error = "Recurso possui associações no BD, acao nao permitida";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}

