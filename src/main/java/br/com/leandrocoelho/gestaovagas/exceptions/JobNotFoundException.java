package br.com.leandrocoelho.gestaovagas.exceptions;

public class JobNotFoundException extends RuntimeException{

    public JobNotFoundException(){
        super("vaga não existe");
    }
}
