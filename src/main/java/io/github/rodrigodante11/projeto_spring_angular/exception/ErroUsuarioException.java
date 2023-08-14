package io.github.rodrigodante11.projeto_spring_angular.exception;

public class ErroUsuarioException extends RuntimeException{
    public ErroUsuarioException(String msg){
        super(msg);
    }
}
