package me.linckode.restapiserver.MySQL.Auth.Tables.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends HttpError {

    public UnauthorizedException(String message){
        super(message);
    }

}
