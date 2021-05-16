package me.linckode.restapiserver.MySQL.Auth.Tables.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends HttpError {
    public ConflictException(String message) {
        super(message);
    }
}
