package br.com.leandrocoelho.gestaovagas.exceptions;

public class UserFoundException extends RuntimeException{

    public UserFoundException(){
        super("Usuário já existe");
    }
}
