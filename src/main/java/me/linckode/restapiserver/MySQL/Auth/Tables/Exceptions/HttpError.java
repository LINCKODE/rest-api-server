package me.linckode.restapiserver.MySQL.Auth.Tables.Exceptions;

public abstract class HttpError extends RuntimeException {

    public HttpError(String message){
        super(message);
    }

}
