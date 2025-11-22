package br.com.leandrocoelho.gestaovagas.modules.candidate.controller;

import br.com.leandrocoelho.gestaovagas.modules.candidate.entities.CandidateEntity;
import br.com.leandrocoelho.gestaovagas.modules.candidate.usecases.CreateCandidateUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    private final CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    public CandidateController(CreateCandidateUseCase createCandidateUseCase){ // construtor da classe
        this.createCandidateUseCase= createCandidateUseCase;
    }

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
        try {
            var result =  this.createCandidateUseCase.execute(candidateEntity);
            return ResponseEntity.ok().body(result);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}