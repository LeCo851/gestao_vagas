package br.com.leandrocoelho.gestaovagas.modules.company.controllers;

import br.com.leandrocoelho.gestaovagas.exceptions.UserFoundException;
import br.com.leandrocoelho.gestaovagas.modules.company.dto.CreateCompanyDTO;
import br.com.leandrocoelho.gestaovagas.modules.company.entities.CompanyEntity;
import br.com.leandrocoelho.gestaovagas.modules.company.usecases.CreateCompanyUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CreateCompanyUseCase createCompanyUseCase;



    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CreateCompanyDTO createCompanyDTO){
        try {

            var companyEntity = CompanyEntity.builder()
                    .username(createCompanyDTO.username())
                    .email(createCompanyDTO.email())
                    .password(createCompanyDTO.password())
                    .name(createCompanyDTO.name())
                    .description(createCompanyDTO.description())
                    .website(createCompanyDTO.website())
                    .build();

            var result = this.createCompanyUseCase.execute(companyEntity);
            return ResponseEntity.ok().body(result);

        } catch (UserFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
