package br.com.matsutech.restwithspringbootjava.handler;

import br.com.matsutech.restwithspringbootjava.exceptions.ExceptionResponse;
import br.com.matsutech.restwithspringbootjava.exceptions.InvalidJwtAuthenticationException;
import br.com.matsutech.restwithspringbootjava.exceptions.ResourceNotFoundEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(
            Exception ex, WebRequest request){
        var exception = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundEntityException.class)
    public  ResponseEntity<ExceptionResponse> handleNotFoundExceptions(
            Exception ex, WebRequest request){
        var exception = new ExceptionResponse(new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidJwtAuthenticationException.class)
    public  ResponseEntity<ExceptionResponse> handleInvalidJwtAuthenticationException(
            Exception ex, WebRequest request){
        var exception = new ExceptionResponse(new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(exception, HttpStatus.FORBIDDEN);
    }

}
