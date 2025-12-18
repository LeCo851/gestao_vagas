package br.com.leandrocoelho.gestaovagas.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(){
        super("Usuário não existe");
    }
}
