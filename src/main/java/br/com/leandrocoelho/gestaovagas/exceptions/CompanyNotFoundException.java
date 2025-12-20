package br.com.leandrocoelho.gestaovagas.exceptions;

public class CompanyNotFoundException extends RuntimeException{

    public CompanyNotFoundException(){
        super("Company not found");
    }
}
