package br.com.leandrocoelho.gestaovagas.modules.company.dto;


public record CreateCompanyDTO(
        String username,
        String email,
        String password,
        String name,
        String description,
        String website
) {
}