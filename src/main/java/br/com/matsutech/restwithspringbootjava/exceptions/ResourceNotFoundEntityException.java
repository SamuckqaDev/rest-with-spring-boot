package br.com.matsutech.restwithspringbootjava.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


import java.io.Serializable;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundEntityException extends RuntimeException implements Serializable {

    private static final long serialVersionUID;

    static {
        serialVersionUID = 1L;
    }

    public ResourceNotFoundEntityException(String ex){
        super(ex);
    }


}
